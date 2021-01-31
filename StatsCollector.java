package OptionPricing;

import java.util.ArrayList;

public class StatsCollector {
	
	private double mean = 0;
	private double var = 0;
	private double meansquare = 0; 
	private ArrayList<Double> stats;
	
	public StatsCollector(){
		this.stats=new ArrayList<Double>();
	}

	 public void add(double x){
		 stats.add(x);
		 mean=((stats.size()-1)*mean+x)/stats.size();
		 meansquare=((stats.size()-1)*meansquare+x*x)/stats.size();
		 var=meansquare-mean*mean;
	}
	 public boolean stop(double p, double error) {
		if(stats.size() <= 0){
			return false;
		}
		return p*Math.sqrt(var/stats.size())<error;
		 
	}
	 public int getNum(){
			return stats.size();
		}
		
		public double getMean(){
			return mean;
		}
		
		public double getSumOfSquare(){
			return meansquare;
		}
		
		public double getVariance(){
			return var;
		}
	 
}
