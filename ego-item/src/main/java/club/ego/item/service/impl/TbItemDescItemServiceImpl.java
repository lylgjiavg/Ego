package club.ego.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.dubbo.service.TbItemDescDubboService;
import club.ego.item.service.TbItemDescItemService;
import club.ego.pojo.TbItemDesc;

public class TbItemDescItemServiceImpl implements TbItemDescItemService {
	
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("${redis.desc.key}")
	private String key;
	
	@Override
	public String showItemDesc(long itemId) {
		
		// 判断Redis是否存在数据
		String descKey = key + itemId;
		if(jedisDaoImpl.exist(descKey)) {
			String desc = jedisDaoImpl.get(descKey);
			if(desc!=null&&!"".equals(desc)) {
				return desc;
			}
		}
		
		// 不存在,数据库查询并添加到Redis
		TbItemDesc itemDesc = tbItemDescDubboService.selectByItemid(itemId);
		
		String desc = itemDesc.getItemDesc();
		// Redis数据不能为null
		if(desc==null) {
			desc = "";
		}
		jedisDaoImpl.set(descKey, desc);
		
		return desc;
	}

}
