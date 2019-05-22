package club.ego.search.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

/**
 * Solr数据进行初始化
 * @author 姜立成
 * @date:   2019年5月14日 上午10:26:07
 *
 */
public interface SolrInitService {
	
	/**
	 * Solr数据初始化
	 */
	void init() throws SolrServerException, IOException;
	
}
