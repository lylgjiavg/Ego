package club.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.dubbo.service.TbItemCatDubboService;
import club.ego.dubbo.service.TbItemParamDubboService;
import club.ego.manage.pojo.TbItemParamChild;
import club.ego.manage.service.TbItemParamManageService;
import club.ego.pojo.TbItemCat;
import club.ego.pojo.TbItemParam;

public class TbItemParamManageServiceImpl implements TbItemParamManageService {
	
	@Reference
	private TbItemParamDubboService tbItemParamDubboService;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		
		EasyUIDataGrid dataGrid = tbItemParamDubboService.showPage(page, rows);
		
		@SuppressWarnings("unchecked")
		List<TbItemParam> itemParam = (List<TbItemParam>) dataGrid.getRows();
		
		List<TbItemParamChild> itemChildList = new ArrayList<TbItemParamChild>();
		TbItemParamChild itemParamChild = null;
		for (TbItemParam tbItemParam : itemParam) {
			TbItemCat itemCat = tbItemCatDubboService.selectById(tbItemParam.getItemCatId());
			
			itemParamChild = new TbItemParamChild();
			itemParamChild.setId(tbItemParam.getId());
			itemParamChild.setItemCatId(tbItemParam.getItemCatId());
			itemParamChild.setParamData(tbItemParam.getParamData());
			itemParamChild.setCreated(tbItemParam.getCreated());
			itemParamChild.setUpdated(tbItemParam.getUpdated());
			
			itemParamChild.setItemCatName(itemCat.getName());
			
			itemChildList.add(itemParamChild);
		}
		
		dataGrid.setRows(itemChildList);
		
		return dataGrid;
	}

	@Override
	public int deleteById(String ids) throws Exception{
		
		return tbItemParamDubboService.deleteById(ids);
	}

	@Override
	public TbItemParam selectByCatId(long catId) {
		
		return tbItemParamDubboService.selectByCatId(catId);
	}

	@Override
	public int insertTbItemParam(TbItemParam itemParam) {
		
		return tbItemParamDubboService.insertTbItemParam(itemParam);
	}

}
