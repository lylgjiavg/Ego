package club.ego.commons.pojo;

/**
 * 返回信息是否更新成功状态值类
 * 
 * @author 姜立成
 * @date: 2019年4月18日 下午4:24:41
 *
 */
public class EgoResult {

	// 状态信息是否更新成功状态
	private int status;

	// 其他信息
	private Object data;
	
	// 错误信息
	private String msg;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "EgoResult [status=" + status + ", data=" + data + ", msg=" + msg + "]";
	}

}
