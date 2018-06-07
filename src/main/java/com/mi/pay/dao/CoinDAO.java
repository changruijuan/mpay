package com.mi.pay.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

/**
 * Created by Ruijuan on 6/4/18.
 */
@DAO
public interface CoinDAO {

	@SQL("select amount from coin where uid = :uid")
	public Integer getAmountByUid(@SQLParam("uid") int uid);

	@SQL("update coin set amount = :amount where uid = :uid")
	public boolean updateAmountByUid(@SQLParam("uid") int uid,
	                                 @SQLParam("amount") int amount);

}
