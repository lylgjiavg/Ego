package club.ego.search.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import club.ego.pojo.TbItem;

/**
 * Search搜索服务
 * @author 姜立成
 * @date:   2019年5月14日 下午2:28:34
 *
 */
public interface SolrSearchService {
	
	/**
	 * 利用查询条件,搜索商品信息
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String, Object> search(String query, int page, int rows) throws SolrServerException, IOException ;
	
	/**
	 * 新增solr数据
	 * @param tbItem
	 * @return
	 */
	int addSolr(LinkedHashMap map, String desc, String categoryName) throws SolrServerException, IOException ;
}
