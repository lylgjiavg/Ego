package club.ego.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.commons.utils.HttpClientUtil;
import club.ego.commons.utils.IDUtils;
import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbItemCatDubboService;
import club.ego.dubbo.service.TbItemDescDubboService;
import club.ego.dubbo.service.TbItemDubboService;
import club.ego.dubbo.service.TbItemParamItemDubboService;
import club.ego.manage.service.TbItemManageService;
import club.ego.pojo.TbItem;
import club.ego.pojo.TbItemDesc;
import club.ego.pojo.TbItemParamItem;

/**
 * 
 * @author 姜立成
 * @date:   2019年4月18日 上午11:19:10
 *
 */
public class TbItemManageServiceImpl implements TbItemManageService{
	
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;
	@Reference
	private TbItemParamItemDubboService tbItemParamItemDubboService;
	
	@Value("${httpclient.url}")
	private String url;
	
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		
		return tbItemDubboService.show(page, rows);
	}
	
	
	@Override
	public boolean updateTbItemStatus(String ids, byte status) {
		
		//数据更新行数
		int index = 0;
		
		//前台多项修改状态的数据为:(ids: 536563,562379),按照规则分割
		String[] idAttrs = ids.split(",");
		
		//修改各个商品状态信息
		TbItem item = new TbItem();
		for (String id : idAttrs) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			tbItemDubboService.updateTbItem(item);
			
			index++;
		}
		
		//判断商品是否全部更新成功
		if(index == idAttrs.length) {
			return true;
		}
		
		return false;
	}


	@Override
	public int insertTbItem(TbItem tbItem, String desc, String itemParams) throws Exception {
		
		int count = 0;
		
		// ======添加事务回滚======
		//设置完整TbItem(商品)信息 
		long id = IDUtils.genItemId(); 
		Date date = new Date();
		
		tbItem.setId(id); 
		tbItem.setStatus((byte)1); 
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		
		//设置完整TbItemDesc(商品卖点)信息 
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id); 
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date); 
		tbItemDesc.setUpdated(date);
		
		//设置完整TbItemParamItem(商品规格数据)信息
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setItemId(id);
		
		count += tbItemDubboService.insertTbItemAndDesc(tbItem, tbItemDesc);
		count += tbItemParamItemDubboService.insertItemParamItem(itemParamItem);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// solr数据添加
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tbItem", tbItem);
				map.put("desc", desc);
				map.put("cat", tbItemCatDubboService.selectById(tbItem.getCid()));
				
				HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
			}
		}).start();
		
		
		return count;
		
		
		/* ======无事务回滚机制======
		 * 
		 * 
		 * //设置完整TbItem信息 long id = IDUtils.genItemId(); Date date = new Date();
		 * 
		 * tbItem.setId(id); tbItem.setStatus((byte)1); tbItem.setCreated(date);
		 * tbItem.setUpdated(date);
		 * 
		 * int count = tbItemDubboService.insertTbItem(tbItem);
		 * 
		 * if(count == 1) { TbItemDesc tbItemDesc = new TbItemDesc();
		 * tbItemDesc.setItemId(id); tbItemDesc.setItemDesc(desc);
		 * tbItemDesc.setCreated(date); tbItemDesc.setUpdated(date);
		 * 
		 * count += tbItemDescDubboService.insertTbItemDesc(tbItemDesc); }
		 * 
		 * if(count == 2) { return 1; }
		 * 
		 * return 0;
		 */
	}
	
}
