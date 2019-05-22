package club.ego.dubbo.service;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.pojo.TbItemParam;

/**
 * 规格参数(TbItemParam)服务类
 * @author 姜立成
 * @date:   2019年4月23日 下午6:24:00
 *
 */
public interface TbItemParamDubboService {
	
	/**
	 * 规格参数显示列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page, int rows);
	
	/**
	 * 根据id数组删除多个规格参数
	 * @param id
	 * @return
	 */
	int deleteById(String ids) throws Exception;
	
	/**
	 * 根据id查找规格参数
	 * @param id
	 * @return
	 */
	TbItemParam selectByCatId(long catId);
	
	/**
	 * 添加规格参数
	 * @param itemParam
	 * @return
	 */
	int insertTbItemParam(TbItemParam itemParam);
	
}
