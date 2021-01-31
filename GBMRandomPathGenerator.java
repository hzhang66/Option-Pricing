package OptionPricing;

import java.util.*;
import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;


public class GBMRandomPathGenerator implements PathGenerator {
	
	// daily parameters
	private double rate;
	private double sigma;
	private double S0;
	private int N;
	private DateTime startDate;
	private RandomVectorGenerator rvg;
	
	//why to write a sub-class?
	public GBMRandomPathGenerator(double rate, int N,
			double sigma, double S0,
			DateTime startDate,
			RandomVectorGenerator rvg){
		this.startDate = startDate;
		this.rate = rate;
		this.S0 = S0;
		this.sigma = sigma;
		this.N = N;
		this.rvg = rvg;
	}

	@Override

	public List<Pair<DateTime, Double>> getPrices() {
		double[] n = rvg.getVector();
		DateTime current = startDate;
		List<Pair<DateTime, Double>> path = new ArrayList<Pair<DateTime,Double>>();
		path.add(new Pair<DateTime, Double>(current, S0));
		for ( int i=1; i < N; ++i){
			current.plusDays(1);
			path.add(new Pair<DateTime, Double>(current, 
					path.get(i-1).getValue()*Math.exp((rate-sigma*sigma/2)+sigma * n[i-1])));
		}
		return path;
	}

}
