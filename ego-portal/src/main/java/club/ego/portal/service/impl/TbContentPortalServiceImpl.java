package club.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbContentDubboService;
import club.ego.pojo.TbContent;
import club.ego.portal.service.TbContentPortalService;

public class TbContentPortalServiceImpl implements TbContentPortalService {
	
	@Reference
	private TbContentDubboService tbContentDubboService;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("{redis.ad.key}")
	private String key;
	
	@Override
	public String showJsonForAD() {
		
		if(jedisDaoImpl.exist(key)) {
			String value = jedisDaoImpl.get(key);
			if(value!=null&&value!="") {
				return value;
			}
		}
		
		// 查询6条数据,按时间排序
		List<TbContent> list = tbContentDubboService.selectForAD(6, true);
		
		List<Map<String, Object>> listJson = new ArrayList<Map<String,Object>>();

		for (TbContent tc : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("srcB", tc.getPic2());
			map.put("height", 240);
			map.put("alt", "加载图片失败");
			map.put("width", 670);
			map.put("src", tc.getPic());
			map.put("widthB", 550);
			
			listJson.add(map);
		}
		String value = JsonUtils.objectToJson(listJson);
		jedisDaoImpl.set(key, value);
		
		return value;
	}

}
