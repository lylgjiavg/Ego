package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.dubbo.service.TbContentDubboService;
import club.ego.mapper.TbContentMapper;
import club.ego.pojo.TbContent;
import club.ego.pojo.TbContentExample;

public class TbContentDubboServiceImpl implements TbContentDubboService {
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public EasyUIDataGrid showContentTable(long categoryId, int page, int rows) {
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		
		// 设置查询条件
		PageHelper.startPage(page, rows);
		
		TbContentExample example = new TbContentExample();
		if(categoryId != 0) {
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		
		List<TbContent> list = tbContentMapper.selectByExample(example);
		
		// 获取查询结果信息
		PageInfo<TbContent> info = new PageInfo<TbContent>(list);
		
		dataGrid.setRows(list);
		dataGrid.setTotal(info.getTotal());
		
		return dataGrid;
	}

	@Override
	public int insertContent(TbContent content) {
		
		return tbContentMapper.insert(content);
	}

	@Override
	public List<TbContent> selectForAD(int count, boolean isdesc) {
		
		TbContentExample example = new TbContentExample();
		//判断排序
		if(isdesc) {
			example.setOrderByClause("updated desc");
		}
		//判断查询个数
		if(count == 0) {
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}else {
			PageHelper.startPage(1, count);
			
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> info = new PageInfo<TbContent>(list);
			
			return info.getList();
		}
		
	}

}
