package club.ego.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.cart.service.CartService;
import club.ego.commons.pojo.EgoResult;
import club.ego.commons.pojo.TbItemChildren;
import club.ego.commons.utils.CookieUtils;
import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbItemDubboService;
import club.ego.pojo.TbItem;
import club.ego.pojo.TbUser;

public class CartServiceImpl implements CartService {

	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("${cart.item.key}")
	private String key;

	@Override
	public void addCart(HttpServletRequest request, long id, int num) {
		
		Cookie[] cookies = request.getCookies();
		String username = "";
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("TT_TOKEN")) {
				String coolieKey = cookie.getValue();
				String userJson = jedisDaoImpl.get(coolieKey);
				TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
				username = user.getUsername();
			}
		}
		
		TbItem item = tbItemDubboServiceImpl.selectById(id);
		// Redis中物品信息的key
		String cartKey = key + username;
		System.out.println(cartKey);
		String keyRedis = jedisDaoImpl.get(cartKey);
		System.out.println("keyRedis"+keyRedis);
		boolean isExist = false;
		if (keyRedis != null && !"".equals(keyRedis)) {
			// Redis中存在购物车信息
			List<TbItemChildren> list = JsonUtils.jsonToList(keyRedis, TbItemChildren.class);
			for (TbItemChildren children : list) {
				if (children.getId() == id) {
					// Redis中购物车信息有此物品信息
					children.setNum(num + children.getNum());
					jedisDaoImpl.set(cartKey, JsonUtils.objectToJson(list));
					isExist = true;
					return;
				} 
			}
			if(!isExist){
				// Redis中购物车信息无此物品信息
				
				TbItemChildren newItem = new TbItemChildren();
				newItem.setId(item.getId());
				newItem.setNum(num);
				newItem.setPrice(item.getPrice());
				newItem.setImages(
						item.getImage() != null && !"".equals(item.getImage()) ? item.getImage().split(",")
								: new String[1]);

				list.add(newItem);

				jedisDaoImpl.set(cartKey, JsonUtils.objectToJson(list));
			}
		} else {
			// Redis中不存在购物车信息
			List<TbItemChildren> list = new ArrayList<TbItemChildren>();

			TbItemChildren children = new TbItemChildren();
			children.setId(item.getId());
			children.setNum(num);
			children.setPrice(item.getPrice());
			children.setImages(item.getImage() != null && !"".equals(item.getImage()) ? item.getImage().split(",")
					: new String[1]);

			list.add(children);

			jedisDaoImpl.set(cartKey, JsonUtils.objectToJson(list));
		}

	}

	@Override
	public List<TbItemChildren> showCart(HttpServletRequest request) {
		
		// 获得Redis中物品信息
		String userKey = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String userJson = jedisDaoImpl.get(userKey);
		String cartKey = key + (JsonUtils.jsonToPojo(userJson, TbUser.class).getUsername());
		String cartJson = jedisDaoImpl.get(cartKey);
		
		List<TbItemChildren> cartList = JsonUtils.jsonToList(cartJson, TbItemChildren.class);
		
		return cartList;
	}

	@Override
	public EgoResult updateCart(long id, int num, HttpServletRequest request) {
		
		// 获得购物车商品信息
		String userKey = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String userJson = jedisDaoImpl.get(userKey);
		String cartKey = key + (JsonUtils.jsonToPojo(userJson, TbUser.class).getUsername());
		String cartJson = jedisDaoImpl.get(cartKey);
		
		// 修改对应的商品数量
		List<TbItemChildren> cartList = JsonUtils.jsonToList(cartJson, TbItemChildren.class);
		for (TbItemChildren cart : cartList) {
			if(cart.getId()==id) {
				cart.setNum(num);
			}
		}
		
		// 把购物车信息重新设置到Redis
		String ok = jedisDaoImpl.set(cartKey, JsonUtils.objectToJson(cartList));
		
		EgoResult result = new EgoResult();
		if(ok == "OK") {
			result.setStatus(200);
		}
		
		return result;
	}

	@Override
	public EgoResult deleteCart(long id, HttpServletRequest request) {

		// 获得购物车商品信息
		String userKey = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String userJson = jedisDaoImpl.get(userKey);
		String cartKey = key + (JsonUtils.jsonToPojo(userJson, TbUser.class).getUsername());
		String cartJson = jedisDaoImpl.get(cartKey);
		
		// 删除对应的商品
		List<TbItemChildren> cartList = JsonUtils.jsonToList(cartJson, TbItemChildren.class);
		TbItemChildren child = new TbItemChildren();
		for (TbItemChildren cart : cartList) {
			if(cart.getId()==id) {
				child = cart;
			}
		}
		boolean remove = cartList.remove(child);
		String ok = jedisDaoImpl.set(cartKey, JsonUtils.objectToJson(cartList));
		
		EgoResult result = new EgoResult();
		if("OK".equals(ok)) {
			result.setStatus(200);
		}
		
		return result;
	}

}
