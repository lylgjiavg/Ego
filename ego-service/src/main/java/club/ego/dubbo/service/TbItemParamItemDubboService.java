package club.ego.dubbo.service;

import club.ego.pojo.TbItemParamItem;

/**
 * 商品规格数据服务
 * @author 姜立成
 * @date:   2019年4月24日 上午10:26:17
 *
 */
public interface TbItemParamItemDubboService {
	
	/**
	 * 添加商品规格数据
	 * @param tbItemParamItem
	 * @return
	 */
	int insertItemParamItem(TbItemParamItem tbItemParamItem);
	
	/**
	 * 根据id查询商品规格数据
	 * @param id
	 * @return
	 */
	TbItemParamItem selectByItemId(long id);
	
}
