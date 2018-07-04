package com.stockapp.calculation;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.stockapp.dto.ScoreDTO;
import com.stockapp.dto.Stock;
import com.stockapp.parser.ReadCSV;

public class PortfolioAnalysisEngine {
	
	
	public static ScoreDTO scoreCalculate(String stockName, String startDate, String endDate, String frequency){
		
		ScoreDTO scoreStock = new ScoreDTO();
		try {
			Stock stockDTO = ReadCSV.parseStock(stockName, startDate, endDate,frequency);
			if (stockDTO == null || stockDTO.getTradeData() == null || stockDTO.getTradeData().size() == 0){
				return scoreStock;
			}
			
			scoreStock = TechAnalysisEngine.technicalAverageScore(stockDTO.getTradeData());
			
		
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scoreStock;
	}
	
	public static Map<String,ScoreDTO> portfolioScoreCalculator(List<String> portfolio,
			String startDate, String endDate, String frequency){
		
		Map<String,ScoreDTO> mapScoreNameStock = new HashMap<String, ScoreDTO>();
		ValueComparator bvc =  new ValueComparator(mapScoreNameStock);
		TreeMap<String,ScoreDTO> sorted_map = new TreeMap<String,ScoreDTO>(bvc);

		final CountDownLatch latch = new CountDownLatch(portfolio.size());
		ExecutorService execute = Executors.newFixedThreadPool(100);
		int currPoint = 0 ;
		for (currPoint = 0; currPoint < portfolio.size(); currPoint++) {
			String stockName = portfolio.get(currPoint);
			
			execute.submit(new Thread(new Runnable() {
				
				@Override
				public void run() {
					ScoreDTO scoreStock = scoreCalculate(stockName, startDate, endDate, frequency);
					System.out.println("Stock: " + stockName + " ; Score: " + scoreStock.getTotalAverageScore());
					mapScoreNameStock.put(stockName, scoreStock);
					
					latch.countDown();
				}
			}));
			
		}
		//execute.shutdown();
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sorted_map.putAll(mapScoreNameStock);
		
		return sorted_map;
	}

}
