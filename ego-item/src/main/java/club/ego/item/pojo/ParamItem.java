package club.ego.item.pojo;

import java.util.List;

/**
 * 商品数据的param_data列数据信息类
 * @author 姜立成
 * @date:   2019年5月16日 上午12:01:38
 *
 */
public class ParamItem {
	
	private String group;
	
	private List<ParamItemKV> params;

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public List<ParamItemKV> getParams() {
		return params;
	}
	public void setParams(List<ParamItemKV> params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "ParamItem [group=" + group + ", params=" + params + "]";
	}
	
}
