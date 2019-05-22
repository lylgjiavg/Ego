package club.ego.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbItemParamItemDubboService;
import club.ego.item.pojo.ParamItem;
import club.ego.item.service.TbItemParamItemItemService;
import club.ego.pojo.TbItemParamItem;

public class TbItemParamItemItemServiceImpl implements TbItemParamItemItemService {
	
	@Reference
	private TbItemParamItemDubboService tbItemParamItemDubboService;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("${redis.paramItem.key}")
	private String key;
	
	@Override
	public List<ParamItem> showItemParamItem(long id) {
		
		// 从Redis中查找数据
		String keyParamItem = key + id;
		if(jedisDaoImpl.exist(keyParamItem)) {
			String paramItem = jedisDaoImpl.get(keyParamItem);
			if(paramItem!=null&&!"".equals(keyParamItem)) {
//				System.out.println(paramItem);
				return JsonUtils.jsonToList(paramItem, ParamItem.class);
			}
		}
		
		// Redis没有数据,从MySQL中查找数据并写入Redis中
		TbItemParamItem paramItem = tbItemParamItemDubboService.selectByItemId(id);
//		System.out.println(id);
//		System.out.println(paramItem);
		String paramData = paramItem.getParamData();
		
		if(paramData==null) {
			paramData = "";
		}
		jedisDaoImpl.set(keyParamItem, paramData);
		System.out.println(paramData);
		return JsonUtils.jsonToList(paramData, ParamItem.class);
	}

}
