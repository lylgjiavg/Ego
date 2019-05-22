package club.ego.commons.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI分页数据类
 * @author 姜立成
 * @date:   2019年4月18日 下午4:10:01
 *
 */
public class EasyUIDataGrid implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//当前页显示数据
	private List<?> rows;
	//总条数
	private long total;
	
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
