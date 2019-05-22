package club.ego.manage.service;

import java.util.List;

import club.ego.commons.pojo.EasyUIDataCat;
import club.ego.commons.pojo.EgoResult;
import club.ego.pojo.TbContentCategory;

/**
 * 内容分类服务
 * @author 姜立成
 * @date:   2019年4月27日 下午10:06:42
 *
 */
public interface TbContentCategoryManageService {
	
	/**
	 * 根据父id查询子项
	 * @param id
	 * @return
	 */
	List<EasyUIDataCat> showConCateChild(long id);
	
	/**
	 * 新增内容分类
	 * @param contentCategory
	 */
	EgoResult createConCate(TbContentCategory contentCategory);
	
	/**
	 * 更新内容分类
	 * @param contentCategory
	 * @return
	 */
	EgoResult updateConCate(TbContentCategory contentCategory);
	
}
