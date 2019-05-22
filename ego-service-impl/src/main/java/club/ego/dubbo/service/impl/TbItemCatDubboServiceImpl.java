package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import club.ego.dubbo.service.TbItemCatDubboService;
import club.ego.mapper.TbItemCatMapper;
import club.ego.pojo.TbItemCat;
import club.ego.pojo.TbItemCatExample;

public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TbItemCat> selectByPid(long pid) {
		
		//设置查询条件(目录的父目录为pid)
		TbItemCatExample itemCatExample = new TbItemCatExample();
		itemCatExample.createCriteria().andParentIdEqualTo(pid);
		
		//根据查询条件查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(itemCatExample);
		
		return list;
	}

	@Override
	public TbItemCat selectById(long id) {
		
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}
