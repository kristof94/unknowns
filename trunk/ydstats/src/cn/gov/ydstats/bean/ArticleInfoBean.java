package cn.gov.ydstats.bean;

import java.util.Date;

public class ArticleInfoBean {
	private Integer id;
	
	/**
	 * 主分类ID
	 */
	private String masterTypeId;
	
	/**
	 * 子分类ID
	 */
	private String slaveTypeId;
	
	/**
	 * 主分类名称
	 */
	private String masterTypeName;
	
	/**
	 * 子分类名称
	 */
	private String slaveTypeName;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 时间
	 */
	private Date createDate;
	
	/**
	 * 内容
	 */
	private String content;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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

	public String getMasterTypeName() {
		return masterTypeName;
	}
	
	public void setMasterTypeName(String masterTypeName) {
		this.masterTypeName = masterTypeName;
	}
	
	public String getSlaveTypeName() {
		return slaveTypeName;
	}
	
	public void setSlaveTypeName(String slaveTypeName) {
		this.slaveTypeName = slaveTypeName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
