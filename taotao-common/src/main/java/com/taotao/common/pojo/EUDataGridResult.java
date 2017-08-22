package com.taotao.common.pojo;

import java.util.List;

/**
 * 
 * @Description:TODO EasyUI 表格通用 Result
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月2日 下午9:22:00   
 * @version:1.0 
 * @param <T>
 */
public class EUDataGridResult<T> implements Result<T> {
	/**
	 * 数据总条数
	 */
	private long total;
	/**
	 * Data List
	 */
	private List<T> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
