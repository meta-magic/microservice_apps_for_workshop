package com.metamagic.productreview.dto;

public class ResponseBean {

	private boolean sucess;
	
	private String message;
	
	private String status;
	
	private Object data;

	public ResponseBean(boolean sucess, String message, String status, Object data) {
		super();
		this.sucess = sucess;
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public boolean isSucess() {
		return sucess;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}
	
}
