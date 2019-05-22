package club.ego.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.item.service.TbItemDescItemService;

@Controller
public class TbItemDescController {

	@Autowired
	private TbItemDescItemService tbItemDescItemServiceImpl;
	
	@RequestMapping(value = "item/desc/{itemId}.html", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String showItemDesc(@PathVariable long itemId) {

		return tbItemDescItemServiceImpl.showItemDesc(itemId);
	}

}
