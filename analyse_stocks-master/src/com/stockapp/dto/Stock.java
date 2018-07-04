package com.stockapp.dto;

import java.util.List;

public class Stock {
	
	String stockName;
	
	List<TradeData> tradeData;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public List<TradeData> getTradeData() {
		return tradeData;
	}

	public void setTradeData(List<TradeData> tradeData) {
		this.tradeData = tradeData;
	}

	@Override
	public String toString() {
		return "Stock [stockName=" + stockName + ", tradeData=" + tradeData
				+ "]";
	}
	
	

}
