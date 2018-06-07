package com.mi.pay.response;

import java.io.Serializable;

/**
 * Created by Ruijuan on 6/6/18.
 */
public class BaseResponse implements Serializable {
	private int code;
	private String msg;

	public BaseResponse(ResponseStatus status) {
		this.code = status.getCode();
		this.msg = status.getMsg();
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
