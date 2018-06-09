package com.mi.pay.controllers;

import com.mi.pay.response.JsonResponse;
import com.mi.pay.service.CoinService;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ruijuan on 6/4/18.
 */

@Path("coin")
public class CoinController {

	@Autowired
	private CoinService coinService;

	/**
	 * 查询用户uid的积分
	 * @param uid 不能为空
	 * @return
	 */
	@Get("amount")
	public String getAmountByUid(@Param("uid") @ParamDesc(isRequest = true) int uid) {
		return JsonResponse.respToString(coinService.getAmountByUid(uid));
	}

	/**
	 * 使用积分交易
	 * @param uid 用户id
	 * @param tid 交易id
	 * @param money 交易积分
	 * @return
	 */
	@Get("pay")
	public String payByCoin(@Param("uid") @ParamDesc(isRequest = true) int uid,
	                        @Param("tid") @ParamDesc(isRequest = true) int tid,
	                        @Param("money") @ParamDesc(isRequest = true, minIntVal = 0) int money) {
		return JsonResponse.respToString(coinService.payByCoin(uid, tid, money));
	}
}
