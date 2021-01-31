package OptionPricing;

import java.util.Arrays;

public class AntiTheticVectorGenerator implements RandomVectorGenerator{
	
	private RandomVectorGenerator rvg;
	private double[] lastVector;


	public AntiTheticVectorGenerator(RandomVectorGenerator rvg){
		this.rvg = rvg;
	}

	
	/*
	* This function aims to cater for generating negation of the previous random variable
	* */
	public double[] getVector() {
		if ( lastVector == null ){
			lastVector = rvg.getVector();
			return lastVector;
		} else {
			double[] tmp =Arrays.copyOf(lastVector, lastVector.length);
			lastVector = null;
			for (int i = 0; i < tmp.length; ++i){ tmp[i] = -tmp[i];}
			return tmp;
		}
	}
}
