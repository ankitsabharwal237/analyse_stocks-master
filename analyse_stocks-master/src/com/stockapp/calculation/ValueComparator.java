package com.stockapp.calculation;

import java.util.Comparator;
import java.util.Map;

import com.stockapp.dto.ScoreDTO;

	class ValueComparator implements Comparator<String> {

	    Map<String, ScoreDTO> base;
	    public ValueComparator(Map<String, ScoreDTO> mapScoreNameStock) {
	        this.base = mapScoreNameStock;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	    	if(base.get(a) == null || base.get(b) == null) return 1;
	    	if(base.get(a).getTotalAverageScore() == null || 
	    			base.get(b).getTotalAverageScore() == null) return 1;
	    	
	        if ((base.get(a)).getTotalAverageScore() >= (base.get(b)).getTotalAverageScore()) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	}
