package club.ego.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import club.ego.commons.pojo.EasyUIDataCat;
import club.ego.commons.pojo.EgoResult;
import club.ego.manage.service.TbContentCategoryManageService;
import club.ego.pojo.TbContentCategory;

@Controller
public class TbContentCategoryController {
	
	@Autowired
	private TbContentCategoryManageService TbContentCategoryManageServiceImpl;
	
	@RequestMapping("content/category/list")
	@ResponseBody
	public List<EasyUIDataCat> showContentCategory(@RequestParam(defaultValue = "0") long id) {
		
		List<EasyUIDataCat> child = TbContentCategoryManageServiceImpl.showConCateChild(id);
		
		return child;
	}
	
	/**
	 * 新增内容节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("content/category/create")
	@ResponseBody
	public EgoResult create(long parentId, String name) {
		
		TbContentCategory cate = new TbContentCategory();
		cate.setName(name);
		cate.setParentId(parentId);
		
		return TbContentCategoryManageServiceImpl.createConCate(cate);
	}
	
	/**
	 * 更新节点信息
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("content/category/update")
	@ResponseBody
	public EgoResult update(long id, String name) {
		
		TbContentCategory cate = new TbContentCategory();
		cate.setId(id);
		cate.setName(name);
		
		return TbContentCategoryManageServiceImpl.updateConCate(cate);
	}
	
}
