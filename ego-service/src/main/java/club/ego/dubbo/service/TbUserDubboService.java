package club.ego.dubbo.service;

import club.ego.pojo.TbUser;

/**
 * 用户(登录,注册)服务
 * @author 姜立成
 * @date:   2019年5月16日 下午11:39:30
 *
 */
public interface TbUserDubboService {
	
	/**
	 * 根据用户名和密码查找用户
	 * @param tbuser
	 * @return
	 */
	TbUser selectByUandP(TbUser tbuser);
	
}
