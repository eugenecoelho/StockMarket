package com.jpmorgan.interview.stockmarket;

/**
 * StockSymbol allows to represent and contorl Stock Symbols
 * 
 * <p>
 * TODO : StockSymbol validation
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public final class StockSymbol {

	private final String stockSymbol;

	/**
	 * Constructor for StockSymbol.
	 * 
	 * @param stockSymbol
	 *            String
	 */
	public StockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * Method getStockSymbol.
	 * 
	 * @return String
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
