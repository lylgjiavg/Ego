package club.ego.dubbo.service;

import java.util.List;

import club.ego.pojo.TbOrder;
import club.ego.pojo.TbOrderItem;
import club.ego.pojo.TbOrderShipping;

/**
 * 订单服务
 * @author 姜立成
 * @date:   2019年5月19日 上午9:04:15
 *
 */
public interface TbOrderDubboService {
	
	/**
	 * 新增订单
	 * @param order	订单信息
	 * @param list	订单物品信息
	 * @param ship	订单收货人信息
	 * @return
	 */
	int insertOrder(TbOrder order, List<TbOrderItem> list, TbOrderShipping ship) throws Exception ;
	
}
