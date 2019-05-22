package club.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.dubbo.service.TbItemCatDubboService;
import club.ego.item.pojo.CatItemDate;
import club.ego.item.pojo.CateGory;
import club.ego.item.service.TbItemCatItemService;
import club.ego.pojo.TbItemCat;

public class TbItemCatItemServiceImpl implements TbItemCatItemService {
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public CateGory selectCateGoay() {
		
		//查找所有根目录
		List<TbItemCat> bootCat = tbItemCatDubboService.selectByPid(0);
		
		//将要返回的数据（如果是文件夹返回List<CatItemDate>|如果不是文件夹返回List<String>）
		List<Object> arrCat = new ArrayList<Object>();
		
		//是文件夹时返回List<CatItemDate>数据
		CatItemDate catDate = null;
		for (TbItemCat tbItemCat : bootCat) {
			System.out.println(tbItemCat);
			//判断此目录下是否还有子目录
			if(tbItemCat.getIsParent()) {
				catDate = new CatItemDate();
				catDate.setU("/products/"+tbItemCat.getId()+".html");
				catDate.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				catDate.setI(recursionCat(tbItemCat));
				
				arrCat.add(catDate);
			}else {
				arrCat.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
			
		}
		
		CateGory cateGory = new CateGory();
		cateGory.setData(arrCat);
		
		return cateGory;
	}
	
	/**
	 * 递归查询子目录
	 * @param itemCat
	 * @return
	 */
	public List<Object> recursionCat(TbItemCat itemCat) {
		
		List<TbItemCat> childrenCat = tbItemCatDubboService.selectByPid(itemCat.getId());
		
		//将要返回的数据（如果是文件夹返回List<CatItemDate>|如果不是文件夹返回List<String>）
		List<Object> arrCat = new ArrayList<Object>();
		
		//是文件夹时返回List<CatItemDate>数据
		CatItemDate catDate = null;
		for (TbItemCat tbItemCat : childrenCat) {
			
			//判断此目录下是否还有子目录
			if(tbItemCat.getIsParent()) {
				catDate = new CatItemDate();
				catDate.setU("/products/"+tbItemCat.getId()+".html");
				catDate.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				catDate.setI(recursionCat(tbItemCat));
				
				arrCat.add(catDate);
			}else {
				arrCat.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
			
		}
		
		return arrCat;
	}
	
}
