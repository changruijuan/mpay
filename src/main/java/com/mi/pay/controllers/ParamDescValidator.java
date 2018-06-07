package com.mi.pay.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.ParamValidator;
import net.paoding.rose.web.paramresolver.ParamMetaData;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

/**
 * Created by Ruijuan on 6/7/18.
 */
public class ParamDescValidator implements ParamValidator {
	private static String PARAM_NULL_ERROR_JSON = "@json: {\"code\": 400, \"msg\": \"参数%s不能为空\"}";
	private static String PARAM_RANGE_ERROR_JSON = "@json: {\"code\": 400, \"msg\": \"参数%s = %s, 要求为[%s, %s]\"}";

	@Override
	public Object validate(ParamMetaData paramMetaData, Invocation invocation, Object o, Errors errors) {
		String paramName = paramMetaData.getParamName();
		String value = invocation.getParameter(paramName);
		ParamDesc paramDesc = paramMetaData.getAnnotation(ParamDesc.class);
		Class<?> paramType = paramMetaData.getParamType();
		if (paramDesc.isRequest()) {
			if (StringUtils.isBlank(value)) {
				return String.format(PARAM_NULL_ERROR_JSON, paramName);
			}
		}
		if (paramType.isAssignableFrom(long.class)) {// 如果类型是long
			long data = Long.parseLong(value);
			if (data > paramDesc.maxLongVal() || data < paramDesc.maxLongVal()) {
				return String.format(PARAM_RANGE_ERROR_JSON, paramName, data, paramDesc.minLongVal(), paramDesc.maxLongVal());
			}
		}
		if (paramType.isAssignableFrom(int.class)) {// 如果类型是int
			int data = Integer.parseInt(value);
			if (data > paramDesc.maxIntVal() || data < paramDesc.minIntVal()) {
				return String.format(PARAM_RANGE_ERROR_JSON, paramName, data, paramDesc.minIntVal(), paramDesc.maxIntVal());
			}
		}
		return null;
	}

	@Override
	public boolean supports(ParamMetaData paramMetaData) {
		return paramMetaData.getAnnotation(ParamDesc.class) != null;
	}
}
