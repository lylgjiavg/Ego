package club.ego.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import club.ego.commons.pojo.TbItemChildren;
import club.ego.order.pojo.OrderInformation;

/**
 * 订单服务类
 * @author 姜立成
 * @date:   2019年5月18日 下午9:34:51
 *
 */
public interface OrderService {

	/**
	 * 根据id显示订单信息
	 * @param ids
	 * @param request
	 * @return
	 */
	List<TbItemChildren> showOrder(Long[] ids, HttpServletRequest request);
	
	/**
	 * 创建订单
	 * @param orderInfo
	 * @param request
	 * @return
	 */
	int createOrder(OrderInformation orderInfo, HttpServletRequest request);
	
}
