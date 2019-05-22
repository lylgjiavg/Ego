package club.ego.manage.service;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.pojo.TbItemParam;

/**
 * 规格参数服务(TbItemParam)
 * @author 姜立成
 * @date:   2019年4月23日 下午6:41:51
 *
 */
public interface TbItemParamManageService {
	
	/**
	 * 获得规格参数列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page, int rows);
	
	/**
	 * 删除规格参数(批量删除)
	 * @param ids
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
