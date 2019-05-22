package club.ego.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.dubbo.service.TbItemDubboService;
import club.ego.mapper.TbItemDescMapper;
import club.ego.mapper.TbItemMapper;
import club.ego.pojo.TbItem;
import club.ego.pojo.TbItemDesc;
import club.ego.pojo.TbItemExample;

public class TbItemDubboServiceImpl implements TbItemDubboService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		
		//设置分页信息
		PageHelper.startPage(page, rows);
		
		//查询所有记录
		List<TbItem> listAll = tbItemMapper.selectByExample(new TbItemExample());
		
		//获得分页后信息
		PageInfo<TbItem> info = new PageInfo<TbItem>(listAll);
		List<TbItem> list = info.getList();
		long total = info.getTotal();
		
		//把分页信息生成一个Easyui数据对象中
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(list);
		dataGrid.setTotal(total);
		
		return dataGrid;
	}

	@Override
	public int updateTbItem(TbItem tbItem) {
		
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	@Override
	public int insertTbItem(TbItem tbItem) {
		
		return tbItemMapper.insert(tbItem);
	}

	@Override
	public int insertTbItemAndDesc(TbItem tbItem, TbItemDesc tbItemDesc) throws Exception {
		
		int count = 0;
		
		try {
			count += tbItemMapper.insertSelective(tbItem);
			count += tbItemDescMapper.insertSelective(tbItemDesc);
		} catch (Exception e) {
			throw new Exception("商品新增失败!<br>事务回滚...");
		}
		
		return count;
	}

	@Override
	public List<TbItem> selectByStatus(byte status) {
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo(status);
		
		return tbItemMapper.selectByExample(example);
	}

	@Override
	public TbItem selectById(long id) {
		
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
