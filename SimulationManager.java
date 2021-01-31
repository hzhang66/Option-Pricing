package OptionPricing;

import org.joda.time.DateTime;

public class SimulationManager {
	// main function
	public static void main(String[] args){
        
		long starttime = System.currentTimeMillis();
		double K = 165;
		int N = 252;
		double S0 = 152.35;
		double rate = 0.0001; 
		double sigma = 0.01;
		double error = 0.01;
		double p = 3;
		DateTime today = new DateTime(2020,06,30,16,59);
		
		// set initial iterations first
		int M = 50000;


		StatsCollector st = new StatsCollector();
	
		//European Call Option
		EuropeanCallOption ec = new EuropeanCallOption(K);
		
		for(int i = 0; i < M; i++){
			RandomVectorGenerator rvg = new NormalRandomNumberGenerator(N);
			RandomVectorGenerator avg = new AntiTheticVectorGenerator(rvg);	
			PathGenerator g = new GBMRandomPathGenerator(rate, N, sigma, S0, today, avg);
			st.add(ec.getPayout(g));
			
		    if(st.stop(p,error)&& i>100){
			    break;
		    }
		    
		}
		// discount based on 252 days
		double ECprice = st.getMean()*Math.exp(-rate*N);
		System.out.println("Price of European Call option is:");
		System.out.printf("%.6f, with %d number of iterations. \n", ECprice, st.getNum());

		// Asian Call Option
		StatsCollector stAsian = new StatsCollector();
		AsianCallOption ac = new AsianCallOption(K);
		
		for(int i = 0; i < M; i++){
			RandomVectorGenerator rvg = new NormalRandomNumberGenerator(N);
			RandomVectorGenerator avg = new AntiTheticVectorGenerator(rvg);	
			PathGenerator g = new GBMRandomPathGenerator(rate, N, sigma, S0, today, avg);
			stAsian.add(ac.getPayout(g));
			
			if(stAsian.stop(p,error)&& i>100){
			    break;
			}
			
		}

		// discount based on 251 days
		double ACprice = stAsian.getMean()*Math.exp(-rate*(N-1));
		System.out.println("Price of Asian Call option is:");
		System.out.printf("%.6f, with %d number of iterations. \n", ACprice , stAsian.getNum());
		
		long endtime = System.currentTimeMillis();
		double executime = (endtime-starttime)/1000;
		//text writer
		TextfileWriter output= new TextfileWriter("Javaoutput");
		output.writeLine("European Call option");
		output.writeLine("Call option price is",ECprice);
		output.writeLine("Number of iterations is ",st.getNum());
		output.writeLine("Asian Call option");
		output.writeLine("Call option price is",ACprice);
		output.writeLine("Number of iterations is ",stAsian.getNum());
		output.writeLine("The execution time of Java is (s)",executime);
		output.finish();
		
	}

}
