package club.ego.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.dubbo.service.TbItemCatDubboService;
import club.ego.dubbo.service.TbItemDescDubboService;
import club.ego.dubbo.service.TbItemDubboService;
import club.ego.pojo.TbItem;
import club.ego.pojo.TbItemCat;
import club.ego.pojo.TbItemDesc;
import club.ego.search.service.SolrInitService;

public class SolrInitServiceImpl implements SolrInitService {
	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Autowired
	private CloudSolrClient client;
	
	@Override
	public void init() throws SolrServerException, IOException {
		
		// 清空先前Solr信息
		client.deleteByQuery("*:*");
		client.commit();
		
		// 获取TBItem数据信息
		List<TbItem> itemList = tbItemDubboServiceImpl.selectByStatus((byte)1);
		// 遍历信息,添加到solr中
		for (TbItem tbItem : itemList) {
			// 查找solr需要的其他信息
			TbItemCat cat = tbItemCatDubboServiceImpl.selectById(tbItem.getCid());
			TbItemDesc desc = tbItemDescDubboServiceImpl.selectByItemid(tbItem.getId());
			// 集成完整solr信息
			SolrInputDocument doc = new SolrInputDocument();
			doc.setField("id", tbItem.getId());
			doc.addField("item_title", tbItem.getTitle());
			doc.addField("item_sell_point", tbItem.getSellPoint());
			doc.addField("item_price", tbItem.getPrice());
			doc.addField("item_image", tbItem.getImage());
			doc.addField("item_category_name", cat.getName());
			doc.addField("item_desc", desc.getItemDesc());
			doc.addField("item_updated", desc.getUpdated());
			
			client.add(doc);
		}
		// 提交
		client.commit();
		
	}

}
