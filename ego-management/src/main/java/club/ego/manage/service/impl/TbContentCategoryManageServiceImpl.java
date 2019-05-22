package club.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.commons.pojo.EasyUIDataCat;
import club.ego.commons.pojo.EgoResult;
import club.ego.commons.utils.IDUtils;
import club.ego.dubbo.service.TbContentCategoryDubboService;
import club.ego.manage.service.TbContentCategoryManageService;
import club.ego.pojo.TbContentCategory;

public class TbContentCategoryManageServiceImpl implements TbContentCategoryManageService {

	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboService;

	@Override
	public List<EasyUIDataCat> showConCateChild(long id) {

		List<EasyUIDataCat> easyuiDateList = new ArrayList<EasyUIDataCat>();

		List<TbContentCategory> categoryList = tbContentCategoryDubboService.selectByPid(id);

		for (TbContentCategory tbContentCategory : categoryList) {
			EasyUIDataCat easyuiDate = new EasyUIDataCat();
			easyuiDate.setId(tbContentCategory.getId());
			easyuiDate.setText(tbContentCategory.getName());
			easyuiDate.setState(tbContentCategory.getIsParent() ? "closed" : "open");

			easyuiDateList.add(easyuiDate);
		}

		return easyuiDateList;
	}

	@Override
	public EgoResult createConCate(TbContentCategory contentCategory) {

		EgoResult result = new EgoResult();
		
		// 查询内容分类在父内容内是否已经存在
		List<TbContentCategory> brotherList = tbContentCategoryDubboService.selectByPid(contentCategory.getParentId());
		for (TbContentCategory brother : brotherList) {
			// 如果新增内容分类名称和兄弟节点一样,使新增失败
			if (brother.getName().equals(contentCategory.getName())) {
				result.setData("<br>失败原因:节点已存在");

				return result;
			}
		}
		
		//获得新增内容分类id(不能使其在数据库自增,因为前台需要此id来进行设置)
		long id = IDUtils.genItemId();
		
		//完善行政内容分类信息
		Date date = new Date();
		contentCategory.setId(id);
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		
		// 新增内容分类
		int index = tbContentCategoryDubboService.insertConCategory(contentCategory);
		if (index > 0) {
			// 更新父节点的是否是文件夹信息
			TbContentCategory fatherCate = new TbContentCategory();
			fatherCate.setId(contentCategory.getParentId());
			fatherCate.setIsParent(true);
			fatherCate.setSortOrder(2);
			
			int fatIndex = tbContentCategoryDubboService.updateConCategory(fatherCate);
			if(fatIndex > 0) {
				result.setStatus(200);
			}
		}

		return result;
	}

	@Override
	public EgoResult updateConCate(TbContentCategory contentCategory) {
		
		EgoResult result = new EgoResult();
		
		//从数据库获得完整的要修改的内容分类信息
		TbContentCategory contentPro = tbContentCategoryDubboService.selectById(contentCategory.getId());
		System.out.println(contentPro);
		// 查询内容分类在父内容内是否已经存在
		List<TbContentCategory> brotherList = tbContentCategoryDubboService.selectByPid(contentPro.getParentId());
		for (TbContentCategory brother : brotherList) {
			System.out.println(brother);
			// 如果新增内容分类名称和兄弟节点一样,使新增失败
			if (brother.getName().equals(contentCategory.getName())) {
				result.setData("<br>失败原因:节点已存在");

				return result;
			}
		}
		
		//更新内容分类节点信息
		Date date = new Date();
		contentPro.setUpdated(date);
		contentPro.setName(contentCategory.getName());
		
		int index = tbContentCategoryDubboService.updateConCategory(contentPro);
		if(index > 0) {
			result.setStatus(200);
		}
		
		return result;
	}

}
