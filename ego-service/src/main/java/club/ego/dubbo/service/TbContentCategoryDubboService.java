package club.ego.dubbo.service;

import java.util.List;

import club.ego.pojo.TbContentCategory;

/**
 * 内容分类(TbContentCategory)服务
 * @author 姜立成
 * @date:   2019年4月27日 下午9:58:40
 *
 */
public interface TbContentCategoryDubboService {
	
	/**
	 * 根据父id查找子内容分类
	 * @param id
	 * @return
	 */
	List<TbContentCategory> selectByPid(long id);
	
	/**
	 * 根据id查找内容分类
	 * @param id
	 * @return
	 */
	TbContentCategory selectById(long id);
	
	/**
	 * 新增内容分类列
	 * @param contentCategory
	 * @return
	 */
	int insertConCategory(TbContentCategory contentCategory);
	
	/**
	 * 更新内容分类
	 * @param contentCategory
	 * @return
	 */
	int updateConCategory(TbContentCategory contentCategory);
	
}
