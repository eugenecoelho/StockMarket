package com.jpmorgan.interview.stockmarket;

import java.util.Date;

/**
 * ITrade is a interface to the Trade object
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public interface ITrade {

	/**
	 * Method getTradetimestamp.
	 * 
	 * @return Date
	 */
	public Date getTradetimestamp();

	/**
	 * Method getStockSymbol.
	 * 
	 * @return StockSymbol
	 */
	public StockSymbol getStockSymbol();

	/**
	 * Method getStockQuantity.
	 * 
	 * @return int
	 */
	public int getStockQuantity();

	/**
	 * Method getIndicator.
	 * 
	 * @return Indicator
	 */
	public Indicator getIndicator();

	/**
	 * Method getPrice.
	 * 
	 * @return Money
	 */
	public Money getPrice();

}
