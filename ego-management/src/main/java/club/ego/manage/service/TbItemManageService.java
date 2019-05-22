package club.ego.manage.service;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.pojo.TbItem;

/**
 * 
 * @author 姜立成
 * @date: 2019年4月18日 上午11:18:54
 *
 */
public interface TbItemManageService {

	/**
	 * 获得当前页商品信息
	 * 
	 * @param page 当前页
	 * @param rows 每页数据量
	 * @return
	 */
	EasyUIDataGrid show(int page, int rows);

	/**
	 * 修改多个商品状态信息
	 * 
	 * @param ids    多个商品id
	 * @param status 商品状态
	 * @return
	 */
	boolean updateTbItemStatus(String ids, byte status);

	/**
	 * 增加商品信息
	 * 
	 * @param tbItem
	 * @param desc
	 * @return
	 * @throws Exception 方便前台进行错误显示
	 */
	int insertTbItem(TbItem tbItem, String desc, String itemParams) throws Exception;

}
