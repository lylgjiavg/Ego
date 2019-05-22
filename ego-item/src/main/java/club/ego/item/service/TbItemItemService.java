package club.ego.item.service;

import club.ego.commons.pojo.TbItemChildren;

/**
 * 商品信息服务
 * @author 姜立成
 * @date:   2019年5月15日 下午4:45:19
 *
 */
public interface TbItemItemService {
	
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	TbItemChildren showItem(long id);
	
}
