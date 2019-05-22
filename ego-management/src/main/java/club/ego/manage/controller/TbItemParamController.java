package club.ego.manage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.commons.pojo.EgoResult;
import club.ego.manage.service.TbItemParamManageService;
import club.ego.pojo.TbItemParam;

@Controller
public class TbItemParamController {
	
	@Autowired
	private TbItemParamManageService tbItemParamManageServiceImpl;
	
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid show(int page, int rows) {
		
		return tbItemParamManageServiceImpl.showPage(page, rows);
	}
	
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		
		int index = 0;
		
		EgoResult itemResult = new EgoResult();
		try {
			index = tbItemParamManageServiceImpl.deleteById(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(index == 1) {
			itemResult.setStatus(200);
		}
		
		return itemResult;
	}
	
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public EgoResult showParam(@PathVariable String catId) {
		
		EgoResult result = new EgoResult();
		
		TbItemParam itemParam = tbItemParamManageServiceImpl.selectByCatId(Long.parseLong(catId));
		
		if(itemParam != null) {
			result.setData(itemParam);
			result.setStatus(200);
		}
		
		return result;
	}
	
	@RequestMapping("item/param/save/{catId}")
	@ResponseBody
	public EgoResult saveItemParam(@PathVariable String catId, String paramData) {
		
		Date date = new Date();
		//组建TbItemParam对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setParamData(paramData);
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParam.setItemCatId(Long.parseLong(catId));
		
		int index = tbItemParamManageServiceImpl.insertTbItemParam(itemParam);
		
		EgoResult result = new EgoResult();
		if(index == 1) {
			result.setStatus(200);
		}
		
		return result;
	}
	
}
