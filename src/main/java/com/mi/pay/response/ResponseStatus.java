package com.mi.pay.response;

/**
 * Created by Ruijuan on 6/6/18.
 */
public enum ResponseStatus {

	SUCC(200, "SUCC"), FAIL(500, "FAIL"), PARAM_ERROE(400, "PARAM ERROR");

	private int code;
	private String msg;

	private ResponseStatus(int code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
