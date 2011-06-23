package org.hld.fab;

import java.io.Serializable;

/**
 * 历史记录
 * @author 华理德
 * @date 2011-6-10 下午01:54:15
 */
public class HistoryLog implements Serializable {
	/**
	 * yyyyMMdd格式的日期
	 */
	private transient String date;
	
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
	private Double money;
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
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
	
	public Double getMoney() {
		return money;
	}
	
	public void setMoney(Double money) {
		this.money = money;
	}
}
