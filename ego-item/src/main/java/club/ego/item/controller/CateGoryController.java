package club.ego.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.item.service.TbItemCatItemService;

@Controller
public class CateGoryController {

	@Autowired
	public TbItemCatItemService tbItemCatItemServiceImpl;
	
	@RequestMapping("rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue CatItemDate(String callback) {
		
		MappingJacksonValue mjv = new MappingJacksonValue(tbItemCatItemServiceImpl.selectCateGoay());
		mjv.setJsonpFunction(callback);
		
		return mjv;
	}
	
}
