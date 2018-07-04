package com.stockapp.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVReader;

import com.stockapp.dto.Stock;
import com.stockapp.dto.TradeData;

public class ReadCSV {
	
	private static List<TradeData> readStockYahoo(String stockName, Date startDate,
			Date endDate, String frequency) throws ParseException{

		String fileLink = "http://real-chart.finance.yahoo.com/table.csv?s=" + 
				stockName + 
				"&a=" + startDate.getMonth() +
				"&b=" + startDate.getDate() + 
				"&c=" + (startDate.getYear()+1900) +
				"&d=" + endDate.getMonth() + 
				"&e=" + endDate.getDate() +
				"&f=" + (endDate.getYear() + 1900)+ 
				"&g=" + frequency +
				"&ignore=.csv";
							
		CSVReader dataStream;
		List<TradeData> listTradeData = new ArrayList<TradeData>();
		try {
			dataStream = urlToStream(fileLink);
			
			String[] row = dataStream.readNext();
			while((row = dataStream.readNext()) != null) {
				if(row == null || row.length != 7) continue;
				if("000".equals(row[5])) continue;
				
			    TradeData tradeDataRow = new TradeData();
			    Date currDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(row[0]);
			    tradeDataRow.setTradeDate(currDate);
			    tradeDataRow.setOpenPrice(Float.parseFloat(row[1]));
			    tradeDataRow.setClosePrice(Float.parseFloat(row[4]));
			    tradeDataRow.setVolume(Integer.parseInt(row[5]));
			    
			    listTradeData.add(0,tradeDataRow);
			}
		} catch (IOException e) {
			System.out.println("File not found for Stock: " + stockName);
		}
		
		
		
		
		
		return listTradeData;
	}
	
	private static CSVReader urlToStream(String fileLink) throws IOException{
		
		CSVReader reader = null;
		URL fileUrl = new URL(fileLink);
		BufferedReader in = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
		reader = new CSVReader(in);
		
		return reader;
		
		
	}
	
	public static Stock parseStock(String stockName, String dateStartMMddyyyy,
			String dateEndMMddyyyy, String frequency) throws ParseException, IOException {

		Date sDate = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(dateStartMMddyyyy);
		Date eDate = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(dateEndMMddyyyy);
		List<TradeData> tradeList = readStockYahoo(stockName, sDate, eDate, frequency);
		
		Stock stock = new Stock();
		stock.setStockName(stockName);
		stock.setTradeData(tradeList);
		
		return stock;
	}
	
	public static List<Stock> parseListStock(List<String> stockNameList, String dateStartDDMMYYYY,
	String dateEndDDMMYYYY, String frequency) throws ParseException, IOException {
		
		List<Stock> listStocks = new ArrayList<Stock>();
		for(String stockName : stockNameList){
			Stock stockCurrent = parseStock(stockName, dateStartDDMMYYYY, dateEndDDMMYYYY, frequency);
			listStocks.add(stockCurrent);
		}
		
		return listStocks;
	}
}
