package club.ego.dubbo.service;

import java.util.List;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.pojo.TbItem;
import club.ego.pojo.TbItemDesc;

/**
 * 商品信息服务类
 * @author 姜立成
 * @date:   2019年4月19日 上午11:13:11
 *
 */
public interface TbItemDubboService {
	
	/**
	 * 商品分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(int page, int rows);
	
	/**
	 * 商品状态更新
	 * @param tbItem
	 * @return
	 */
	int updateTbItem(TbItem tbItem);
	
	/**
	 * 新增商品信息
	 * @param tbItem
	 * @return
	 */
	int insertTbItem(TbItem tbItem);
	
	/**
	 * 新增商品信息和商品卖点(可以使新增进行事务回滚)
	 * @param tbItem
	 * @param tbItemDesc
	 * @return
	 */
	int insertTbItemAndDesc(TbItem tbItem, TbItemDesc tbItemDesc) throws Exception ;
	
	/**
	 * 根据商品状态获取TbItem所有数据
	 * @param status
	 * @return
	 */
	List<TbItem> selectByStatus(byte status);
	
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	TbItem selectById(long id);
	
}
