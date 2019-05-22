package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import club.ego.dubbo.service.TbItemParamItemDubboService;
import club.ego.mapper.TbItemParamItemMapper;
import club.ego.pojo.TbItemParamItem;
import club.ego.pojo.TbItemParamItemExample;

public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService {
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public int insertItemParamItem(TbItemParamItem tbItemParamItem) {
		
		return tbItemParamItemMapper.insert(tbItemParamItem);
	}

	@Override
	public TbItemParamItem selectByItemId(long id) {
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(id);
		
		List<TbItemParamItem> result = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(result!=null) {
			return result.get(0);
		}
		return null;
	}

}
