package OptionPricing;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;

/*
 * The interface for creating StockPath. The returned list ordered by date
 */
public interface PathGenerator {
	
	public List<Pair<DateTime, Double>> getPrices();
	
}