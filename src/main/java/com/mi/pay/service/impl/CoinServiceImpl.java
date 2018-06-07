package com.mi.pay.service.impl;

import com.mi.pay.dao.CoinDAO;
import com.mi.pay.dao.TradeDAO;
import com.mi.pay.model.Trade;
import com.mi.pay.response.ObjectResponse;
import com.mi.pay.response.ResponseStatus;
import com.mi.pay.service.CoinService;
import com.mi.pay.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ruijuan on 6/5/18.
 */
@Service
public class CoinServiceImpl implements CoinService{
	@Autowired
	private CoinDAO coinDAO;

	@Autowired
	private TradeDAO tradeDAO;

	@Override
	public ObjectResponse getAmountByUid(int uid) {
		Integer amount = coinDAO.getAmountByUid(uid);
		ObjectResponse objectResponse = new ObjectResponse(ResponseStatus.SUCC);
		if (amount == null) {
			objectResponse = new ObjectResponse(ResponseStatus.PARAM_ERROE);
			objectResponse.setObject("uid不存在");
		} else {
			objectResponse.setObject(amount);
		}
		return objectResponse;
	}
	
	@Override
	public ObjectResponse payByCoin(int uid, int tid, int money) {
		ObjectResponse objectResponse = new ObjectResponse(ResponseStatus.SUCC);
		Trade trade = getTradeByTid(tid);
		// 判断status状态
		if (trade == null) { //如果不存在tid交易纪录，插入一条
			trade = new Trade(tid, uid, ConstantUtil.PAY_STATUS_ING);
			tradeDAO.insertTrade(trade);
		} else if (trade.getUid() != uid) {
			objectResponse = new ObjectResponse(ResponseStatus.FAIL);
			objectResponse.setObject(String.format("交易记录tid对应的uid和参数uid不一致，" +
					"tid: %s，trade.getUid(): %s, uid: %s", tid, trade.getUid(), uid));
			return objectResponse;
		} else {
			int status = trade.getStatus();
			if (status == ConstantUtil.PAY_STATUS_FAIL || status == ConstantUtil.PAY_STATUS_CLOSED) {
				tradeDAO.updateStatus(tid, ConstantUtil.PAY_STATUS_ING);
			} else {
				objectResponse = new ObjectResponse(ResponseStatus.FAIL);
				if (status == ConstantUtil.PAY_STATUS_SUCC) {
					objectResponse.setObject("交易已成功，请不要重复提交");
				} else if (status == ConstantUtil.PAY_STATUS_ING) {
					objectResponse.setObject("正在处理交易，请不要重复提交");
				} else { //交易状态未知
					objectResponse.setObject(String.format("交易状态未知，tid: %s，status: %s", tid, status));
				}
				return objectResponse;
			}
		}

		// 判断amount积分余额是否足够
		Integer amount = coinDAO.getAmountByUid(uid);
		if (amount == null) {
			objectResponse = new ObjectResponse(ResponseStatus.PARAM_ERROE);
			objectResponse.setObject(String.format("积分表中uid不存在，uid: %s", uid));
			tradeDAO.updateStatus(tid, ConstantUtil.PAY_STATUS_CLOSED);
			return objectResponse;
		} else if (amount < money) {
			objectResponse = new ObjectResponse(ResponseStatus.FAIL);
			objectResponse.setObject(String.format("积分余额不足，amount: %s，money: %s", amount, money));
			tradeDAO.updateStatus(tid, ConstantUtil.PAY_STATUS_FAIL);
			return objectResponse;
		}

		coinDAO.updateAmountByUid(uid, amount - money);
		trade = new Trade(uid, tid, ConstantUtil.PAY_STATUS_SUCC, money, amount, amount - money);
		tradeDAO.updateTrade(trade);
		objectResponse.setObject("交易成功");
		return objectResponse;
	}

	private Trade getTradeByTid(int tid) {
		List<Trade> trades = tradeDAO.getStatusAndUidByTid(tid);
		if (trades == null || trades.size() <= 0) {
			return null;
		} else {
			return trades.get(0);
		}
	}
}
