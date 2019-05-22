package club.ego.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.cart.service.CartService;
import club.ego.commons.pojo.EgoResult;
import club.ego.commons.pojo.TbItemChildren;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartServiceImpl;
	
	// cart/add/155793462889048.html?num=1
	@RequestMapping("cart/add/{id}.html")
	private String addCart(@PathVariable long id, HttpServletRequest request) {
		
		cartServiceImpl.addCart(request, id, Integer.parseInt(request.getParameter("num")));
		
		return "cartSuccess";
	}
	
	@RequestMapping("cart/cart.html")
	private String showCart(HttpServletRequest request, Model model) {
		
		List<TbItemChildren> cartList = cartServiceImpl.showCart(request);
		model.addAttribute("cartList", cartList);
		
		return "cart";
	}
	
	@RequestMapping("cart/update/num/{id}/{num}.action")
	@ResponseBody
	private EgoResult updateCart(@PathVariable long id, @PathVariable int num, HttpServletRequest request) {
		
		return cartServiceImpl.updateCart(id, num, request);
	}
	
	@RequestMapping("cart/delete/{id}.action")
	@ResponseBody
	private EgoResult deleteCart(@PathVariable long id, HttpServletRequest request) {
		
		return cartServiceImpl.deleteCart(id, request);
	}
	
}
