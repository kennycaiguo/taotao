package com.taotao.common.pojo;
/**
 * EasyUI 树形控件节点格式
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月8日 下午9:05:36   
 * @version:1.0
 */
public class EUTreeNode {
	private long id;
	private String text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
}
