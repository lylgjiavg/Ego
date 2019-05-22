package club.ego.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import club.ego.commons.pojo.EgoResult;
import club.ego.commons.pojo.TbItemChildren;

/**
 * 购物车服务
 * @author 姜立成
 * @date:   2019年5月17日 下午5:04:10
 *
 */
public interface CartService {
	
	/**
	 * 添加物品到购物车
	 * @param id
	 */
	void addCart(HttpServletRequest request, long id, int num);
	
	/**
	 * 显示购物车物品信息
	 * @param request
	 * @return
	 */
	List<TbItemChildren> showCart(HttpServletRequest request);
	
	/**
	 * 修改购物车商品数量
	 * @param id
	 * @param num
	 * @return
	 */
	EgoResult updateCart(long id, int num, HttpServletRequest request);
	
	/**
	 * 删除购物车商品
	 * @param id
	 * @return
	 */
	EgoResult deleteCart(long id, HttpServletRequest request);
	
}
