package club.ego.manage.service;

import java.util.List;

import club.ego.commons.pojo.EasyUIDataCat;

/**
 * 商品类目(TbItemCat)相关服务
 * @author 姜立成
 * @date:   2019年4月19日 上午11:20:12
 *
 */
public interface TbItemCatManageService {
	
	/**
	 * 根据父目录查找子目录
	 * @param pid
	 * @return
	 */
	List<EasyUIDataCat> selectByPid(long pid);
	
}
