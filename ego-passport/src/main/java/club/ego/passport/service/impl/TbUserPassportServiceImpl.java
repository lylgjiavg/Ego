package club.ego.passport.service.impl;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.commons.pojo.EgoResult;
import club.ego.commons.utils.CookieUtils;
import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbUserDubboService;
import club.ego.passport.service.TbUserPassportService;
import club.ego.pojo.TbUser;

public class TbUserPassportServiceImpl implements TbUserPassportService {

	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	@Autowired
	private JedisDao jedisDaoImpl;

	@Override
	public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {

		// 创建返回对象
		EgoResult result = new EgoResult();

		// 查找数据库中用户信息
		TbUser resultUser = tbUserDubboServiceImpl.selectByUandP(tbUser);

		// 利用cookie+Redis实现session,添加用户登录状态
		String uuid = UUID.randomUUID().toString();
		String json = JsonUtils.objectToJson(resultUser);
		CookieUtils.setCookie(request, response, "TT_TOKEN", uuid, 60 * 60 * 24 * 7);
		jedisDaoImpl.set(uuid, json);

		if (resultUser != null) {
			result.setStatus(200);
		}
		result.setData("用户名或密码错误!");

		return result;
	}

	@Override
	public EgoResult loginStatus(String token) {
		
		// 创建返回对象
		EgoResult result = new EgoResult();
		
		// 从Redis中获取用户信息
		String userJson = jedisDaoImpl.get(token);
		
		if(userJson!=null&&!"".equals(userJson)) {
			// 完善返回信息
			TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
			user.setPassword("");
			result.setData(user);
		}
		result.setMsg("OK");
		result.setStatus(200);
		
		return result;
	}

	@Override
	public EgoResult loginout(String token, HttpServletRequest request, HttpServletResponse response) {
		
		// 创建返回对象
		EgoResult result = new EgoResult();

		// 删除Cookie和Redis中的信息
		CookieUtils.deleteCookie(request, response, token);
		if(jedisDaoImpl.exist(token)) {
			jedisDaoImpl.del(token);
		}

		result.setStatus(200);
		result.setMsg("OK");
		
		return result;
		
	}

}
