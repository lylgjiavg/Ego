package club.ego.manage.service;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.pojo.TbContent;

/**
 * 内容分类数据(TbContent)服务
 * @author 姜立成
 * @date:   2019年4月28日 下午4:07:34
 *
 */
public interface TbContentManageService {
	
	/**
	 * 获得内容分类数据(TbContent)表格中的数据
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showContentCatalog(long categoryId, int page, int rows);
	
	/**
	 * 添加内容分类数据
	 * @param content
	 * @return
	 */
	int insertContent(TbContent content);
}
