package club.ego.item.service;

/**
 * 商品描述信息服务
 * @author 姜立成
 * @date:   2019年5月15日 下午10:33:09
 *
 */
public interface TbItemDescItemService {
	
	/**
	 * 根据商品id查询描述信息
	 * @param itemId
	 * @return
	 */
	String showItemDesc(long itemId);
	
}
