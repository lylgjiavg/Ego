package club.ego.cart.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import club.ego.commons.pojo.EgoResult;
import club.ego.commons.utils.HttpClientUtil;
import club.ego.commons.utils.JsonUtils;

/**
 * 拦截所有未登陆用户,禁止加入物品到购物车
 * @author 姜立成
 * @date:   2019年5月17日 下午4:27:41
 *
 */
public class CartInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
//			System.out.println(cookie.getName());
			if("TT_TOKEN".equals(cookie.getName())) {
//				System.out.println(cookie.getValue());
				String resultJson = HttpClientUtil.doGet("http://localhost:8084/user/token/"+cookie.getValue());
				EgoResult result = JsonUtils.jsonToPojo(resultJson, EgoResult.class);
//				System.out.println(result);
				if(result.getData()!=null) {
					return true;
				}
			}
		}
		
		response.sendRedirect("http://localhost:8084/user/showLogin");
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
