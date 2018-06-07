package com.mi.pay.service;

import com.mi.pay.response.ObjectResponse;

/**
 * Created by Ruijuan on 6/4/18.
 */

public interface CoinService {

	public ObjectResponse getAmountByUid(int uid);

	public ObjectResponse payByCoin(int uid, int tid, int money);
}
