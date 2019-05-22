package club.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;

import club.ego.dubbo.service.TbItemDescDubboService;
import club.ego.manage.service.TbItemDescManageService;

public class TbItemDescManageServiceImpl implements TbItemDescManageService {
	
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;
	

}
