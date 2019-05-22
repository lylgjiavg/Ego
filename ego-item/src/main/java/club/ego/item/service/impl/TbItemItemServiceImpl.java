package club.ego.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.redis.dao.JedisDao;

import club.ego.commons.pojo.TbItemChildren;
import club.ego.commons.utils.JsonUtils;
import club.ego.dubbo.service.TbItemDubboService;
import club.ego.item.service.TbItemItemService;
import club.ego.pojo.TbItem;

public class TbItemItemServiceImpl implements TbItemItemService {
	
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Autowired
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String key;
	
	@Override
	public TbItemChildren showItem(long id) {
		
		// 给数据添加Redis缓存
		String itemKey = key + id;
		if(jedisDaoImpl.exist(itemKey)) {
			String itemRedis = jedisDaoImpl.get(itemKey);
			if(itemRedis!=null&&!"".equals(itemRedis)) {
				return JsonUtils.jsonToPojo(itemRedis, TbItemChildren.class);
			}
		}
		// 商品信息转换,因为jsp页面image是数组
//		System.out.println(tbItemDubboService);
//		System.out.println(id);
		TbItem item = tbItemDubboService.selectById(id);
		
		TbItemChildren children = new TbItemChildren();
		children.setId(item.getId());
		children.setTitle(item.getTitle());
		children.setSellPoint(item.getSellPoint());
		children.setPrice(item.getPrice());
		children.setImages(item.getImage()!=null&&item.getImage()!=""?item.getImage().split(","):new String[1]);
		children.setUpdated(item.getUpdated());
		
		jedisDaoImpl.set(itemKey, JsonUtils.objectToJson(children));
		
		return children;
	}

}
