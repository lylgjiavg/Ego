package club.ego.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.commons.pojo.EgoResult;
import club.ego.manage.service.impl.TbItemManageServiceImpl;
import club.ego.pojo.TbItem;

@Controller
public class TbItemController {

	@Autowired
	private TbItemManageServiceImpl tbItemManageServiceImpl;
	
	
	/**
	 * TbItem商品列表信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid show(int page, int rows) {
		
		return tbItemManageServiceImpl.show(page, rows);
	}
	
	/**
	 * 商品刪除
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		
		EgoResult itemResult = new EgoResult();
		
		boolean result = tbItemManageServiceImpl.updateTbItemStatus(ids, (byte)3);
		
		//修改成功,修改返回状态为200(成功)
		if(result) {
			itemResult.setStatus((byte)200);
		}
		
		return itemResult;
	}
	
	/**
	 * 商品下架
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/instock")
	@ResponseBody
	public EgoResult instock(String ids) {
		
		EgoResult itemResult = new EgoResult();
		
		boolean result = tbItemManageServiceImpl.updateTbItemStatus(ids, (byte)2);
		
		//修改成功,修改返回状态为200(成功)
		if(result) {
			itemResult.setStatus((byte)200);
		}
		
		return itemResult;
	}
	
	/**
	 * 商品上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelf(String ids) {
		
		EgoResult itemResult = new EgoResult();
		
		boolean result = tbItemManageServiceImpl.updateTbItemStatus(ids, (byte)1);
		
		//修改成功,修改返回状态为200(成功)
		if(result) {
			itemResult.setStatus((byte)200);
		}
		
		return itemResult;
	}
	
	@RequestMapping("item/save")
	@ResponseBody
	public EgoResult save(TbItem tbItem, String desc, String itemParams) {
		EgoResult result = new EgoResult();
		
		int index = 0;
		try {
			index = tbItemManageServiceImpl.insertTbItem(tbItem, desc, itemParams);
		} catch (Exception e) {
			result.setData(e.getMessage());
		}
		
		if(index == 3) {
			result.setStatus(200);
		}
		
		return result;
	}
	
	
}
