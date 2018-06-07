package com.mi.pay.response;

import java.io.Serializable;

/**
 * Created by Ruijuan on 6/6/18.
 */
public class ObjectResponse extends BaseResponse implements Serializable{

	private Object object;

	public ObjectResponse(ResponseStatus status) {
		super(status);
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
