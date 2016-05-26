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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockSymbol other = (StockSymbol) obj;
		if (stockSymbol == null) {
			if (other.stockSymbol != null)
				return false;
		} else if (!stockSymbol.equals(other.stockSymbol))
			return false;
		return true;
	}
}
