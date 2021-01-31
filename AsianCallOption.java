package OptionPricing;

import java.util.List;
import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;

public class AsianCallOption {
    
	private double K;
	
	public AsianCallOption(double K){
		this.K = K;
	}

	public double getPayout(PathGenerator path) {
		List<Pair<DateTime, Double>> prices = path.getPrices();
		double sum = 0.0;
		// calculate the average of prices
		for (int i = 0; i < prices.size(); i++){
			sum += prices.get(i).getValue();
		}
		
		double avg = sum/prices.size();
		return Math.max(0, avg - K);
	}

}
