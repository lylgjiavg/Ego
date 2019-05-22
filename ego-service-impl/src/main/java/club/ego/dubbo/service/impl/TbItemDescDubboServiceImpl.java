package club.ego.dubbo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import club.ego.dubbo.service.TbItemDescDubboService;
import club.ego.mapper.TbItemDescMapper;
import club.ego.pojo.TbItemDesc;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public int insertTbItemDesc(TbItemDesc tbItemDesc) {
		
		return tbItemDescMapper.insert(tbItemDesc);
	}

	@Override
	public TbItemDesc selectByItemid(long itemid) {
		
		return tbItemDescMapper.selectByPrimaryKey(itemid);
	}

}
