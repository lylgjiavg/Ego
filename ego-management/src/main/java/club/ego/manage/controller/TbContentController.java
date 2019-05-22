package club.ego.manage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.commons.pojo.EgoResult;
import club.ego.manage.service.TbContentManageService;
import club.ego.pojo.TbContent;

@Controller
public class TbContentController {
	
	@Autowired
	private TbContentManageService tbContentManageServiceImpl;
	
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid showCatalog(long categoryId, int page, int rows) {
		
		return tbContentManageServiceImpl.showContentCatalog(categoryId, page, rows);
	}
	
	@RequestMapping("content/save")
	@ResponseBody
	public EgoResult contentAdd(TbContent content) {
		
		EgoResult result = new EgoResult();
		
		Date date = new Date();
		content.setUpdated(date);
		content.setCreated(date);
		
		int index = tbContentManageServiceImpl.insertContent(content);
		if(index > 0) {
			result.setStatus(200);
		}
		
		return result;
	}
	
	
}
