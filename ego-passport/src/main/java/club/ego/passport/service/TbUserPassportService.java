package club.ego.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.ego.commons.pojo.EgoResult;
import club.ego.pojo.TbUser;

/**
 * 用户服务
 * @author 姜立成
 * @date:   2019年5月16日 下午11:45:36
 *
 */
public interface TbUserPassportService {
	
	/**
	 * 根据用户名和密码登录
	 * @param tbItem
	 * @return
	 */
	EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 根据token获得Redis中的用户登录信息
	 * @param token
	 * @return
	 */
	EgoResult loginStatus(String token);
	
	/**
	 * 退出登录,清楚cookie和Redis数据
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	EgoResult loginout(String token, HttpServletRequest request, HttpServletResponse response);
	
}
