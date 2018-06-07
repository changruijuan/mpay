package com.mi.pay.controllers;

import java.lang.annotation.*;

/**
 * Created by Ruijuan on 6/7/18.
 */
@Target( { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamDesc {

	// 是否必填
	public boolean isRequest() default false;
	// 最小值long
	public long minLongVal() default Long.MIN_VALUE;
	// 最大值long
	public long maxLongVal() default Long.MAX_VALUE;
	public int minIntVal() default Integer.MIN_VALUE;
	public int maxIntVal() default Integer.MAX_VALUE;
	// 描述
	public String desc() default "";

}
