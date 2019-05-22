package club.ego.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import club.ego.commons.pojo.TbItemChildren;
import club.ego.order.pojo.OrderInformation;
import club.ego.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderServiceImpl;
	
	@RequestMapping("/order/order-cart.html")
	public String showOrder(Long id[], Model model, HttpServletRequest request) {
		
		List<TbItemChildren> list = orderServiceImpl.showOrder(id, request);
		
		model.addAttribute("cartList", list);
		
		return "order-cart";
	}
	
	@RequestMapping("order/create.html")
	public String createOrder(OrderInformation orderInfo, HttpServletRequest request) {
		
		System.out.println(orderInfo);
		int index = orderServiceImpl.createOrder(orderInfo, request);
		
		if(index>0) {
			return "success";
		}else {
			return "exception";
		}
	}
	
}
