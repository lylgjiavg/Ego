package club.ego.dubbo.service;

import club.ego.pojo.TbItemDesc;

/**
 * TbItemDesc(商品卖点表)服务类
 * @author 姜立成
 * @date:   2019年4月21日 下午8:46:45
 *
 */
public interface TbItemDescDubboService {
	
	/**
	 * 新增商品卖点信息
	 * @param tbItemDesc
	 * @return
	 */
	int insertTbItemDesc(TbItemDesc tbItemDesc);
	
	/**
	 * 根据商品ID查找对应的描述信息
	 * @param itemid
	 * @return
	 */
	TbItemDesc selectByItemid(long itemid);
	
}
