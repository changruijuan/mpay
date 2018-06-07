package com.mi.pay.model;

/**
 * Created by Ruijuan on 6/6/18.
 */
public class Trade {

	private int tid;
	private int uid;
	private int status;
	private int money;
	private int amountbf;
	private int amountaf;

	public Trade() {
	}

	public Trade(int uid, int tid, int status, int money, int amountbf, int amountaf) {
		this.uid = uid;
		this.tid = tid;
		this.status = status;
		this.money = money;
		this.amountbf = amountbf;
		this.amountaf = amountaf;
	}

	public Trade(int uid, int tid, int status) {
		this.uid = uid;
		this.tid = tid;
		this.status = status;
	}

	public int getMoney() {
		return money;
	}

	public int getTid() {
		return tid;
	}

	public int getUid() {
		return uid;
	}

	public int getStatus() {
		return status;
	}

	public int getAmountbf() {
		return amountbf;
	}

	public int getAmountaf() {
		return amountaf;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setAmountbf(int amountbf) {
		this.amountbf = amountbf;
	}

	public void setAmountaf(int amountaf) {
		this.amountaf = amountaf;
	}
}
