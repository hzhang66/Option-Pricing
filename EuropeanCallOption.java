package OptionPricing;
import java.util.List;
import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;

public class EuropeanCallOption {
	
    private double K;
	
	public EuropeanCallOption(double K){
		this.K = K;
	}

	public double getPayout(PathGenerator path) {
		List<Pair<DateTime, Double>> prices = path.getPrices();
		int len = prices.size();
		return Math.max(0, prices.get(len-1).getValue() - K);
	}

}
