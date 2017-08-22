package com.taotao.common.pojo;

public class KingEditorUploadFileResult implements Result {
	/**
	 * 0 上传成功, 1 上传失败
	 */
	private String code;
	/**
	 * 上传失败, 提示信息
	 */
	private String message;
	/**
	 * 上传成功, 图片 URL
	 */
	private String url;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
