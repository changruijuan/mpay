package com.mi.pay.dao;

import com.mi.pay.model.Trade;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import java.util.List;

/**
 * Created by Ruijuan on 6/6/18.
 */
@DAO
public interface TradeDAO {

	@SQL("select status from trade where tid = :tid")
	public Integer getStatusByTid(@SQLParam("tid") int tid);

	@SQL("select tid, uid, status from trade where tid = :tid")
	public List<Trade> getStatusAndUidByTid(@SQLParam("tid") int tid);

	@ReturnGeneratedKeys
	@SQL("insert into trade(tid, uid, status) values(:t.tid, :t.uid, :t.status)")
	public Integer insertTrade(@SQLParam("t") Trade trade);

	@SQL("update trade set status = :status where tid = :tid")
	public void updateStatus(@SQLParam("tid") int tid,
	                         @SQLParam("status") int status);

//	@SQL("update trade set status = :t.status" +
//			//"#if(:t.money >= 0) {, money = :t.money} " +
//			//"#if(:t.amountbf >= 0) {, amountbf = :t.amountbf}" +
//			//"#if(:t.amountaf >= 0) {, amountaf = :t.amountaf}" +
//			"where tid = :t.tid")
	@SQL("update trade set status = :t.status" +
			"#if(:t.money >= 0) {, money = :t.money}" +
			"#if(:t.amountbf >= 0) {, amountbf = :t.amountbf}" +
			"#if(:t.amountaf >= 0) {, amountaf = :t.amountaf}" +
			" where tid = :t.tid")
	public void updateTrade(@SQLParam("t") Trade trade);
}
