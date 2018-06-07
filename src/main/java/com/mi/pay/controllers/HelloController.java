package com.mi.pay.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * Created by Ruijuan on 6/4/18.
 */
@Path("hello")
public class HelloController {

	@Get("world")
	public String index() {
		return "@hello world";
	}
}

