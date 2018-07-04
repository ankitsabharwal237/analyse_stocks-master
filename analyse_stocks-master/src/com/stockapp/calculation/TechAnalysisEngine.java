package com.stockapp.calculation;

import java.util.List;

import com.stockapp.dto.ScoreDTO;
import com.stockapp.dto.TradeData;

public class TechAnalysisEngine {
	
	/**
	 * This method will return the score out of 100. It will compare the stock
	 * with an ideal hypothetical stock with following properties: - Always
	 * increasing( +1 for every positive day) adjusted for 100
	 * 
	 * @param currentStock
	 * @return
	 */
	private static Double stabilityScoreCalculatorUpTrend(List<TradeData> tradeDataStock, Integer frequency) {
		
		int countPos = 0;
		
		float prevPrice = (tradeDataStock.get(0) != null) ? (tradeDataStock
				.get(0).getClosePrice()) : 0;
		
		int counter = 0;
		for ( int i = frequency; i < tradeDataStock.size(); i = i + frequency) {
			counter++;
			float currentPrice  = (tradeDataStock.get(i) != null) ? (tradeDataStock
					.get(i).getClosePrice()) : 0;
			
			if(currentPrice > prevPrice*1.01){
				countPos++;
			}
			if(currentPrice > prevPrice*1.05){
				countPos++;
			}
			if(currentPrice > prevPrice*1.07){
				countPos++;
			}

			
			prevPrice = currentPrice;
		}
		
		return (100.0/counter)*(countPos);

	}
	
	/**This Method will calculate the growth of stock for more then a factorPercentage over a period.
	 * The score will increase when a stock moves above by a factor mentioned in set of trading sessions
	 * and will decrease if stock falls.
	 * @param currentStock
	 * @param frequency
	 * @param factorPercentage
	 * @return
	 */
	private static Double growthScoreCalculatorUpTrend(List<TradeData> tradeDataStock, Integer frequency, Double factorPercentage) {
				
		int countPos = 0;
		
		float prevPrice = (tradeDataStock.get(0) != null) ? (tradeDataStock
				.get(0).getClosePrice()) : 0;
		
		int counter = 0;
		for ( int i = frequency; i < tradeDataStock.size(); i = i + frequency) {
			counter++;
			float currentPrice  = (tradeDataStock.get(i) != null) ? (tradeDataStock
					.get(i).getClosePrice()) : 0;
			
			if(currentPrice > prevPrice*factorPercentage){
				countPos++;
			}
			if(currentPrice > prevPrice*2*factorPercentage){
				countPos++;
			}
			if(currentPrice > prevPrice*5*factorPercentage){
				countPos++;
			}
//			else if(prevPrice > currentPrice*factorPercentage){
//				countPos--;
//			}
			
			prevPrice = currentPrice;
		}
		
		return (100.0/counter)*((float)countPos);

	}
	
	public static void stabilityScore(List<TradeData> tradeDataStock,ScoreDTO score){
		
		score.setDailyStabilityScore(stabilityScoreCalculatorUpTrend(tradeDataStock, 1));
		score.setWeeklyStabilityScore(stabilityScoreCalculatorUpTrend(tradeDataStock, 5));
		
		
	}
	
	public static void growthScore(List<TradeData> tradeDataStock,ScoreDTO score){
		int totalTrades = tradeDataStock.size();
		int freq = 0;
		Double sumScore = 0.0;
		if (totalTrades > 15) {
			freq++;
			score.setGrowthScore15session(growthScoreCalculatorUpTrend(tradeDataStock, 15, 1.10));
			sumScore = sumScore +  score.getGrowthScore15session();
		}
		if (totalTrades > 30) {
			freq = freq + 1;
			score.setGrowthScore30session(growthScoreCalculatorUpTrend(tradeDataStock, 30, 1.20));
			sumScore = sumScore +  1* score.getGrowthScore30session() ;
		}
		if (totalTrades > 100) {
			freq = freq + 1;
			score.setGrowthScore100session(growthScoreCalculatorUpTrend(tradeDataStock, 100, 1.75));
			sumScore = sumScore +  1*score.getGrowthScore100session();
		}
		if (totalTrades > 200) {
			freq = freq + 1;
			score.setGrowthScore200session(growthScoreCalculatorUpTrend(tradeDataStock, 200, 2.5));
			sumScore = sumScore + 1*score.getGrowthScore200session();
		}
		score.setAverageGrowthScore(sumScore/freq);
	}
	

	public static ScoreDTO technicalAverageScore(List<TradeData> tradeDataStock){
		ScoreDTO score = new ScoreDTO();
		stabilityScore(tradeDataStock, score);
		growthScore(tradeDataStock, score);
		
		return score;
	}
}
