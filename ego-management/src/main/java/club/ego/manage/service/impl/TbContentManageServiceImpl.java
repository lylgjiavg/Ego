package club.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.commons.pojo.EasyUIDataGrid;
import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbContentDubboService;
import club.ego.manage.service.TbContentManageService;
import club.ego.pojo.TbContent;

public class TbContentManageServiceImpl implements TbContentManageService {

	@Reference
	private TbContentDubboService TbContentDubboService;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("{redis.ad.key}")
	private String key;

	@Override
	public EasyUIDataGrid showContentCatalog(long categoryId, int page, int rows) {

		return TbContentDubboService.showContentTable(categoryId, page, rows);
	}

	@Override
	public int insertContent(TbContent content) {
		// 获取Redis数据库内TbContent数据
		String listJson = jedisDaoImpl.get(key);
		// 创建Redis转换Json所需map
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("srcB", content.getPic2());
		map.put("height", 240);
		map.put("alt", "加载图片失败");
		map.put("width", 670);
		map.put("src", content.getPic());
		map.put("widthB", 550);
		// 判断Redis内是否有轮播图数据
		List<HashMap> list = null;
		if (listJson != null && listJson != "") {
			list = JsonUtils.jsonToList(listJson, HashMap.class);
			
			list.add(0, map);
			// 判断轮播图是否数量超出
			if(list.size()==7) {
				list.remove(list.size()-1);
			}
		}else {
			list = new ArrayList<HashMap>();
			list.add(map);
		}
		jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		System.out.println(JsonUtils.objectToJson(list));
		return TbContentDubboService.insertContent(content);
	}

}
