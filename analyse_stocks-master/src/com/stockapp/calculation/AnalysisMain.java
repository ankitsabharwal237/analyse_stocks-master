package com.stockapp.calculation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.stockapp.dto.Portfolio;
import com.stockapp.dto.ScoreDTO;

public class AnalysisMain {

	
	public static void main(String[] args) throws IOException {
		String startDate = "1/1/2014";
		String endDate = "11/18/2014";
		Map<String, ScoreDTO> score = PortfolioAnalysisEngine.
				portfolioScoreCalculator(Portfolio.portfolioAllStock(), startDate, endDate, "d");
		
		List<String[]> listStocks = new ArrayList<String[]>();
		
		String header[] = {"StockName","TotalScore","AverageStability","AverageGrowth","DailyStability","WeeklyStability",
				"Growth15Session","Growth30Session","Growth100Session","Growth200Session"
		};
		
		for (Map.Entry<String,ScoreDTO> entry : score.entrySet()) {
			 String[] strArray = new String[header.length];
		     int index = 0;
		     strArray[index++] = entry.getKey();
		     
		     ScoreDTO stockScore = entry.getValue();
		     
		     strArray[index++] = (stockScore.getTotalAverageScore()).toString();
		     strArray[index++] = (stockScore.getAverageStabilityScore()).toString();
		     
		     strArray[index++] = (stockScore.getAverageGrowthScore()).toString();
		     
		     strArray[index++] = (stockScore.getDailyStabilityScore()).toString();
		     strArray[index++] = (stockScore.getWeeklyStabilityScore()).toString();
		     strArray[index++] = (stockScore.getGrowthScore15session()).toString();
		     strArray[index++] = (stockScore.getGrowthScore30session()).toString();
		     strArray[index++] = (stockScore.getGrowthScore100session()).toString();
		     strArray[index++] = (stockScore.getGrowthScore200session()).toString();
		     
		     listStocks.add(strArray);
		}
		
		//ExcelReportService.generateReport(listStocks, header, "StockScore_"+startDate+"_"+endDate);
		ExcelReportService.generateReport(listStocks, header, "StockScore");
	}

}
