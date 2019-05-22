package club.ego.dubbo.service;

import java.util.List;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.pojo.TbContent;

/**
 * 内容分类数据(TbContent)服务
 * @author 姜立成
 * @date:   2019年4月28日 下午3:03:30
 *
 */
public interface TbContentDubboService {
	
	/**
	 * 查询内容分类数据
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showContentTable(long categoryId, int page, int rows);
	
	/**
	 * 添加内容分类数据
	 * @param content
	 * @return
	 */
	int insertContent(TbContent content);
	
	/**
	 * 查询初始界面广告轮巡图
	 * @param count		:查询数量
	 * @param isdesc	:是否按时间排序
	 * @return
	 */
	List<TbContent> selectForAD(int count, boolean isdesc);
	
}
