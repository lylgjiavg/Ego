package club.ego.order.pojo;

import java.util.List;

import club.ego.pojo.TbOrderItem;
import club.ego.pojo.TbOrderShipping;

/**
 * 订单信息类
 * @author 姜立成
 * @date:   2019年5月19日 上午9:01:13
 *
 */
public class OrderInformation {
	
	private String payment;
	private int paymentType;
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	@Override
	public String toString() {
		return "OrderInformation [payment=" + payment + ", paymentType=" + paymentType + ",\n orderItems=" + orderItems
				+ ",\n orderShipping=" + orderShipping + "]";
	}
	
}
