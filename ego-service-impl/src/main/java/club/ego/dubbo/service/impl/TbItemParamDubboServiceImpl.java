package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.dubbo.service.TbItemParamDubboService;
import club.ego.mapper.TbItemParamMapper;
import club.ego.pojo.TbItemParam;
import club.ego.pojo.TbItemParamExample;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
	
	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		
		//利用分页工具添加分页数据(开始页,每页显示行数)
		PageHelper.startPage(page, rows);
		
		//添加所要查询的数据***WithBLOBs()代表所查询的数据中带有text,不然所查询的text为null
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		
		//进行查询,获得查询后数据
		PageInfo<TbItemParam> info = new PageInfo<TbItemParam>(list);
		
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(info.getList());
		dataGrid.setTotal(info.getTotal());
		
		return dataGrid;
	}

	@Override
	public int deleteById(String ids) throws Exception {
		
		int index = 0;
		
		String[] idArr = ids.split(",");
		
		for (String id : idArr) {
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		
		if(index == idArr.length) {
			return 1;
		}else {
			throw new Exception();
		}
	}

	@Override
	public TbItemParam selectByCatId(long catId) {
		
		//设置查询条件
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		TbItemParam itemParam = null;
		if(list != null && list.size() != 0) {
			itemParam = list.get(0);
		}
		
		return itemParam;
	}

	@Override
	public int insertTbItemParam(TbItemParam itemParam) {
		
		return tbItemParamMapper.insert(itemParam);
	}


}
