package OptionPricing;

import java.util.Random;

public class NormalRandomNumberGenerator implements RandomVectorGenerator {
	
	private int N;
	private double[] array;
	
	public NormalRandomNumberGenerator(int newN){
		  this.array=new double[newN];
		  this.N = newN;
	  }
	
	Random rand = new Random();
	
	@Override
	public double[] getVector() {
		
		for (int i =0;i<N;i++){
			array[i]=rand.nextGaussian();
		}
		
		return array;
	}
   
}