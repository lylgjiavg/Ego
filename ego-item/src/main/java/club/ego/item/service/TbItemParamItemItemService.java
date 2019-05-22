package club.ego.item.service;

import java.util.List;

import club.ego.item.pojo.ParamItem;

/**
 * 商品数据服务
 * @author 姜立成
 * @date:   2019年5月15日 下午11:53:39
 *
 */
public interface TbItemParamItemItemService {
	
	/**
	 * 根据id查询商品数据
	 * @param id
	 * @return
	 */
	List<ParamItem> showItemParamItem(long id);
	
}
