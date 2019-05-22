package club.ego.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.commons.pojo.TbItemChildren;
import club.ego.commons.utils.CookieUtils;
import club.ego.commons.utils.IDUtils;
import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbItemDubboService;
import club.ego.dubbo.service.TbOrderDubboService;
import club.ego.order.pojo.OrderInformation;
import club.ego.order.service.OrderService;
import club.ego.pojo.TbOrder;
import club.ego.pojo.TbOrderItem;
import club.ego.pojo.TbOrderShipping;
import club.ego.pojo.TbUser;

public class OrderServiceImpl implements OrderService {

	@Reference
	private TbOrderDubboService tbOrderDubboService;
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Value("${cart.item.key}")
	private String key;
	@Autowired
	private JedisDao jedisDaoImpl;
	
	@Override
	public List<TbItemChildren> showOrder(Long[] ids, HttpServletRequest request) {
		
		String userKey = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String userJson = jedisDaoImpl.get(userKey);
		String cartKey = key + JsonUtils.jsonToPojo(userJson, TbUser.class).getUsername(); 
		String cartJson = jedisDaoImpl.get(cartKey);
		
		List<TbItemChildren> orderList = new ArrayList<TbItemChildren>();
		
		List<TbItemChildren> cartList = JsonUtils.jsonToList(cartJson, TbItemChildren.class);
		
		for (TbItemChildren tbItem : cartList) {
			for (long id : ids) {
//				System.out.println("id:"+id+",itemId"+tbItem.getId());
				if((long)id==(long)tbItem.getId()) {
					if(tbItem.getNum()<=tbItemDubboService.selectById(id).getNum()) {
						tbItem.setEnough(true);
					}
					orderList.add(tbItem);
				}
			}
		}
		
		return orderList;
	}

	@Override
	public int createOrder(OrderInformation orderInfo, HttpServletRequest request) {

		// 订单id
		String orderId = UUID.randomUUID().toString();
		// 订单时间
		Date date = new Date();
		// 用户信息
		String userKey = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String userJson = jedisDaoImpl.get(userKey);
		TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
		// 购物车信息
		String cartKey = key + user.getUsername(); 
		String cartJson = jedisDaoImpl.get(cartKey);
		List<TbItemChildren> cartList = JsonUtils.jsonToList(cartJson, TbItemChildren.class);
		
		// 创建订单对象并完善基本信息
		TbOrder order = new TbOrder();
		order.setOrderId(orderId);
		order.setPayment(orderInfo.getPayment());
		order.setPaymentType(orderInfo.getPaymentType());
		order.setStatus(2);
		order.setCreateTime(date);
		order.setUpdateTime(date);
		order.setUserId(user.getId());
		order.setBuyerNick(user.getUsername());
		
		// 创建订单物品对象并完善信息
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		for (TbOrderItem orderItem : orderItems) {
			orderItem.setId(IDUtils.genItemId() + "");
			orderItem.setOrderId(orderId);
		}
		
		// 创建订单收货人对象并完善信息
		TbOrderShipping orderShip = orderInfo.getOrderShipping();
		orderShip.setOrderId(orderId);
		orderShip.setCreated(date);
		orderShip.setUpdated(date);
		
		// 删除Redis相应物品信息
		List<TbItemChildren> remove = new ArrayList<TbItemChildren>();
		for (TbOrderItem orderItem : orderItems) {
			for (TbItemChildren redisCart : cartList) {
				if(Long.parseLong(orderItem.getItemId())==redisCart.getId().longValue()) {
					System.out.println("orderItem:"+orderItem+",redisCart"+redisCart);
					remove.add(redisCart);
				}
			}
		}
		for (TbItemChildren removeChild : remove) {
			cartList.remove(removeChild);
		}
		
		// 删除后的购物车信息,设置回Redis中
		jedisDaoImpl.set(cartKey, JsonUtils.objectToJson(cartList));
		
//		System.out.println("========================");
//		System.out.println(order);
//		System.out.println(orderItems);
//		System.out.println(orderShip);
//		System.out.println("========================");
		
		try {
			tbOrderDubboService.insertOrder(order, orderItems, orderShip);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
