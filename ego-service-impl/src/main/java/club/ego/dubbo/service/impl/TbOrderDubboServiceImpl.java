package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import club.ego.dubbo.service.TbOrderDubboService;
import club.ego.mapper.TbOrderItemMapper;
import club.ego.mapper.TbOrderMapper;
import club.ego.mapper.TbOrderShippingMapper;
import club.ego.pojo.TbOrder;
import club.ego.pojo.TbOrderItem;
import club.ego.pojo.TbOrderShipping;

public class TbOrderDubboServiceImpl implements TbOrderDubboService {
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper; 
	
	@Override
	public int insertOrder(TbOrder order, List<TbOrderItem> list, TbOrderShipping ship) throws Exception {
		int index = 0;
		index = tbOrderMapper.insert(order);
		
		for (TbOrderItem tbOrderItem : list) {
			index += tbOrderItemMapper.insertSelective(tbOrderItem);
		}
		
		index += tbOrderShippingMapper.insert(ship);
		
		if(index==2+list.size()) {
			return 1;
		}else{
			throw new Exception("订单创建失败!");
		}
		
	}

}
