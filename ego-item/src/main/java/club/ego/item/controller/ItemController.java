package club.ego.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import club.ego.item.service.TbItemItemService;

@Controller
public class ItemController {
	
	@Autowired
	private TbItemItemService tbItemItemServiceImpl;
	
	@RequestMapping("item/{id}.html")
	public String item(@PathVariable long id, Model model) {
		
		model.addAttribute("item", tbItemItemServiceImpl.showItem(id));
		
		return "item";
	}
	
}
