package club.ego.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.commons.pojo.EasyUIDataCat;
import club.ego.manage.service.TbItemCatManageService;

@Controller
public class TbItemCatController {
	
	@Autowired
	private TbItemCatManageService tbItemCatManageServiceImpl;
	
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<EasyUIDataCat> showTree(@RequestParam(defaultValue="0") long id){
		
		return tbItemCatManageServiceImpl.selectByPid(id);
	}
	
}
