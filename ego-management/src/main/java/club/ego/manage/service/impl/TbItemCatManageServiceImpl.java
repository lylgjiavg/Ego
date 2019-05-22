package club.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.commons.pojo.EasyUIDataCat;
import club.ego.dubbo.service.TbItemCatDubboService;
import club.ego.manage.service.TbItemCatManageService;
import club.ego.pojo.TbItemCat;

public class TbItemCatManageServiceImpl implements TbItemCatManageService {
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public List<EasyUIDataCat> selectByPid(long pid) {
		
		List<TbItemCat> list = tbItemCatDubboService.selectByPid(pid);
		
		List<EasyUIDataCat> catTree = new ArrayList<EasyUIDataCat>();
		
		for (TbItemCat itemCat : list) {
			EasyUIDataCat easyuiCat = new EasyUIDataCat();
			easyuiCat.setId(itemCat.getId());
			easyuiCat.setText(itemCat.getName());
			easyuiCat.setState(itemCat.getIsParent()?"closed":"open");
			
			catTree.add(easyuiCat);
		}
		
		return catTree;
	}

}
