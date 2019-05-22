package club.ego.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.commons.pojo.TbItemChildren;
import club.ego.dubbo.service.TbItemCatDubboService;
import club.ego.dubbo.service.TbItemDescDubboService;
import club.ego.search.service.SolrSearchService;

public class SolrSearchServiceImpl implements SolrSearchService {
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Autowired
	private CloudSolrClient client;

	@Override
	public Map<String, Object> search(String query, int page, int rows) throws SolrServerException, IOException {

		SolrQuery param = new SolrQuery();
		// 设置查询条件
		param.setQuery("item_keywords:" + query);
		param.setStart(rows * (page - 1));
		param.setRows(rows);
		param.setSort("item_updated", ORDER.asc);
		// 设置高亮条件
		param.setHighlight(true);
		param.addHighlightField("item_title");
		param.setHighlightSimplePre("<span style='color:red'>");
		param.setHighlightSimplePost("</span>");

		List<TbItemChildren> list = new ArrayList<TbItemChildren>();
		// 利用查询结果,设置回显数据信息
		QueryResponse response = client.query(param);

		// 普通回显信息
		SolrDocumentList docList = response.getResults();

		// 高亮回显信息
		Map<String, Map<String, List<String>>> hh = response.getHighlighting();

		for (SolrDocument doc : docList) {
			TbItemChildren itemChild = new TbItemChildren();
			itemChild.setId(Long.parseLong(doc.getFieldValue("id").toString()));

			// title回显高亮
			List<String> hhList = hh.get(doc.getFieldValue("id")).get("item_title");
			if (hhList != null && hhList.size() > 0) {
				itemChild.setTitle(hhList.get(0));
			} else {
				itemChild.setTitle(doc.getFieldValue("item_title").toString());
			}

			String[] images = doc.getFieldValue("item_image").toString().split(",");
			itemChild.setImages(
					doc.getFieldValue("item_image").toString() != "" && images.length > 0 ? images : new String[1]);
			itemChild.setSellPoint(doc.getFieldValue("item_sell_point").toString());
			itemChild.setPrice(Long.parseLong(doc.getFieldValue("item_price").toString()));

			list.add(itemChild);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemList", list);
		map.put("page", page);
		map.put("totalPages",
				docList.getNumFound() % rows == 0 ? docList.getNumFound() / rows : docList.getNumFound() / rows + 1);

		return map;
	}

	@Override
	public int addSolr(LinkedHashMap map, String desc, String categoryName) throws SolrServerException, IOException {
		
		// 创建要新增的solr文档对象
		SolrInputDocument doc = new SolrInputDocument();
		
		// 完善对象信息
		doc.addField("id", map.get("id"));
		doc.addField("item_title", map.get("title"));
		doc.addField("item_sell_point", map.get("sellPoint"));
		doc.addField("item_price", map.get("price"));
		doc.addField("item_image", map.get("image"));
		doc.addField("item_desc", desc);
		doc.addField("item_category_name", categoryName);
		
		// 提交
		client.add(doc);
		UpdateResponse response = client.commit();
		// 新增成功
		if(response.getStatus()==0) {
			return 1;
		}
		// 新增失败
		return 0;
	}

}
