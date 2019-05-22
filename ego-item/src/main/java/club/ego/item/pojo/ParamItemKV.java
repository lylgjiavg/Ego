package club.ego.item.pojo;

/**
 * 商品数据的param_data列数据信息类-->内置类
 * @author 姜立成
 * @date:   2019年5月16日 上午12:04:49
 *
 */
public class ParamItemKV {
	
	private String k;
	private String v;
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	
	@Override
	public String toString() {
		return "ParamItemKV [k=" + k + ", v=" + v + "]";
	}
	
}
