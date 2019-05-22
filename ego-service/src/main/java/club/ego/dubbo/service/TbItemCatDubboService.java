package club.ego.dubbo.service;

import java.util.List;

import club.ego.pojo.TbItemCat;

/**
 * 商品菜单树状图服务类
 * @author 姜立成
 * @date:   2019年4月19日 上午11:05:27
 *
 */
public interface TbItemCatDubboService {
	
	/**
	 * 根据父目录查找子目录
	 * @param pid
	 * @return
	 */
	List<TbItemCat> selectByPid(long pid);
	
	/**
	 * 根据id查找TbItemCat
	 * @param id
	 * @return
	 */
	TbItemCat selectById(long id);
	
}
