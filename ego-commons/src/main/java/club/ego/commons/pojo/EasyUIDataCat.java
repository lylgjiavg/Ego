package club.ego.commons.pojo;

/**
 * EasyUI商品类目信息类
 * @author 姜立成
 * @date:   2019年4月19日 上午11:34:53
 *
 */
public class EasyUIDataCat {
	
	private Long id;
	
	private String text;
	
	private String state;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "EasyUIDataCat [id=" + id + ", text=" + text + ", state=" + state + "]";
	}
	
}
