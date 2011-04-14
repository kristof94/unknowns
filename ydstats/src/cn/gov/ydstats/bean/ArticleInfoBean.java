package cn.gov.ydstats.bean;

public class ArticleInfoBean {
	private String id;
	
	/**
	 * 主分类ID
	 */
	private String masterTypeId;
	
	/**
	 * 子分类ID
	 */
	private String slaveTypeId;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 时间
	 */
	private String date;
	
	/**
	 * 内容
	 */
	private String content;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMasterTypeId() {
		return masterTypeId;
	}

	public void setMasterTypeId(String masterTypeId) {
		this.masterTypeId = masterTypeId;
	}

	public String getSlaveTypeId() {
		return slaveTypeId;
	}

	public void setSlaveTypeId(String slaveTypeId) {
		this.slaveTypeId = slaveTypeId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
