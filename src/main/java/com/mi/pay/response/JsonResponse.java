package com.mi.pay.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Ruijuan on 6/6/18.
 */
public class JsonResponse {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final String RESP_ERROR_JSON = "@json: {\"code\": 500, \"msg\": \"内部错误\"}";

	public static String respToString(BaseResponse baseResponse){
		try {
			return "@json:" + OBJECT_MAPPER.writeValueAsString(baseResponse);

		} catch (JsonProcessingException e) {
			return RESP_ERROR_JSON;
		}
	}
}
