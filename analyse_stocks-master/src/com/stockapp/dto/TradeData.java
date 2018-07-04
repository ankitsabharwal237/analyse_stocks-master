package com.stockapp.dto;

import java.util.Date;

public class TradeData {
	
	private Date tradeDate;
	
	private Float openPrice;
	
	private Float closePrice;
	
	private Integer volume;

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Float getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Float openPrice) {
		this.openPrice = openPrice;
	}

	public Float getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Float closePrice) {
		this.closePrice = closePrice;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "TradeData [tradeDate=" + tradeDate + ", openPrice=" + openPrice
				+ ", closePrice=" + closePrice + ", volume=" + volume + "]";
	}
	
	
}
