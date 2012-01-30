

import java.io.Serializable;

/**
 * 历史记录
 * @author 华理德
 * @date 2011-6-10 下午01:54:15
 */
public class HistoryLog implements Serializable {
	
	/**
	 * 主分类
	 */
	private String master;
	
	/**
	 * 子分类
	 */
	private String slave;
	
	/**
	 * 金额
	 */
	private String money;
	
	public String getMaster() {
		return master;
	}
	
	public void setMaster(String master) {
		this.master = master;
	}
	
	public String getSlave() {
		return slave;
	}
	
	public void setSlave(String slave) {
		this.slave = slave;
	}
	
	public String getMoney() {
		return money;
	}
	
	public void setMoney(String money) {
		this.money = money;
	}
}
