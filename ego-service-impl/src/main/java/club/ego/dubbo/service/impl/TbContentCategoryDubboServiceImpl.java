package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import club.ego.dubbo.service.TbContentCategoryDubboService;
import club.ego.mapper.TbContentCategoryMapper;
import club.ego.pojo.TbContentCategory;
import club.ego.pojo.TbContentCategoryExample;

public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TbContentCategory> selectByPid(long id) {
		
		//创建查询条件
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id);
		
		//根据查询条件查询结果
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		
		return list;
	}

	@Override
	public int insertConCategory(TbContentCategory contentCategory) {
		
		return tbContentCategoryMapper.insert(contentCategory);
	}

	@Override
	public int updateConCategory(TbContentCategory contentCategory) {
		
		return tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
	}

	@Override
	public TbContentCategory selectById(long id) {
		
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}

}
