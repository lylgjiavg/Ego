package club.ego.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import club.ego.portal.service.TbContentPortalService;

@Controller
public class TbContentController {
	
	@Autowired
	private TbContentPortalService tbContentPortalServiceImpl;
	
	@RequestMapping("adJson")
	public String adForJson(Model model) {
		
		model.addAttribute("ad1", tbContentPortalServiceImpl.showJsonForAD());
		
		return "index";
	}
	
}
