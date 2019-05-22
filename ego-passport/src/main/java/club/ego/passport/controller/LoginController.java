package club.ego.passport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.commons.pojo.EgoResult;
import club.ego.passport.service.TbUserPassportService;
import club.ego.pojo.TbUser;

@Controller
public class LoginController {

	@Autowired
	private TbUserPassportService tbUserPassportServiceImpl;

	@RequestMapping("user/showLogin")
	public String showLogin(Model model, @RequestHeader("Referer") String url) {

		System.out.println(url);
		model.addAttribute("redirect", url);

		return "login";
	}

	@RequestMapping("user/login")
	@ResponseBody
	public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {

		return tbUserPassportServiceImpl.login(tbUser, request, response);
	}
	
	@RequestMapping("user/token/{token}")
	@ResponseBody
	public Object loginStatus(String callback, @PathVariable String token) {
		
		EgoResult result = tbUserPassportServiceImpl.loginStatus(token);
		
		if(callback!=null&&!"".equals(callback)) {
			MappingJacksonValue mjv = new MappingJacksonValue(result);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		
		return result;
	}
	
	@RequestMapping("user/logout/{token}")
	@ResponseBody
	public Object loginout(@PathVariable String token, String callback, HttpServletRequest request, HttpServletResponse response) {

		EgoResult result = tbUserPassportServiceImpl.loginout(token, request, response);
		
		if(callback!=null&&!"".equals(callback)) {
			MappingJacksonValue mjv = new MappingJacksonValue(result);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		
		return result;
	}
	
}
