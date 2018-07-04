package com.stockapp.dto;

public class ScoreDTO implements Comparable<ScoreDTO> {
	
	private Double totalAverageScore = 0.0;
	
	private Double totalTechnicalScore = 0.0;
	
	private Double dailyStabilityScore = 0.0;
	
	private Double weeklyStabilityScore = 0.0;
	
	private Double averageStabilityScore = 0.0;
	
	private Double averageGrowthScore = 0.0;
	
	private Double growthScore15session = 0.0;
	
	private Double growthScore30session = 0.0;
	
	private Double growthScore100session = 0.0;
	
	private Double growthScore200session = 0.0;

	public Double getTotalAverageScore() { 
		this.totalAverageScore = (this.averageGrowthScore + getAverageStabilityScore())/2;
		return totalAverageScore;
	}

	public Double getTotalTechnicalScore() {
		return totalTechnicalScore;
	}

	public void setTotalTechnicalScore(Double totalTechnicalScore) {
		this.totalTechnicalScore = totalTechnicalScore;
	}

	public Double getDailyStabilityScore() {
		return dailyStabilityScore;
	}

	public void setDailyStabilityScore(Double dailyStabilityScore) {
		this.dailyStabilityScore = dailyStabilityScore;
	}

	public Double getWeeklyStabilityScore() {
		return weeklyStabilityScore;
	}

	public void setWeeklyStabilityScore(Double weeklyStabilityScore) {
		this.weeklyStabilityScore = weeklyStabilityScore;
	}

	public Double getAverageStabilityScore() {
		
		this.averageStabilityScore = (this.dailyStabilityScore*(.4) + this.weeklyStabilityScore*(.6));
		return averageStabilityScore;
		
	}
	
	public void setAverageGrowthScore(Double averageGrothScore){
		this.averageGrowthScore = averageGrothScore;
	}
	
	public Double getAverageGrowthScore() {
		
		return averageGrowthScore;
	}

	public Double getGrowthScore15session() {
		return growthScore15session;
	}

	public void setGrowthScore15session(Double growthScore15session) {
		this.growthScore15session = growthScore15session;
	}

	public Double getGrowthScore30session() {
		return growthScore30session;
	}

	public void setGrowthScore30session(Double growthScore30session) {
		this.growthScore30session = growthScore30session;
	}

	public Double getGrowthScore100session() {
		return growthScore100session;
	}

	public void setGrowthScore100session(Double growthScore100session) {
		this.growthScore100session = growthScore100session;
	}

	public Double getGrowthScore200session() {
		return growthScore200session;
	}

	public void setGrowthScore200session(Double growthScore200session) {
		this.growthScore200session = growthScore200session;
	}

	@Override
	public int compareTo(ScoreDTO o) {
		return (this.totalAverageScore < o.totalAverageScore) ? -1 : ((this.totalAverageScore == o.totalAverageScore) ? 0 : 1);
	}
	
	
	

}
