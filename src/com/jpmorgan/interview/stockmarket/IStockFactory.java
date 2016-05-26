package com.jpmorgan.interview.stockmarket;

import com.jpmorgan.interview.stockmarket.exception.CreateStockException;

/**
 * IStockFactory interface that allows to create new stock
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public interface IStockFactory {
	/**
	 * Method createStock.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param stockType
	 *            StockType
	 * @param parValue
	 *            Money
	 * @param lastDividend
	 *            Money
	 * @param fixedDividendPercentage
	 *            double
	 * 
	 * 
	 * @return IStock * @throws CreateStockException
	 */
	public IStock createStock(StockSymbol stockSymbol, StockType stockType, Money parValue, Money lastDividend,
			double fixedDividendPercentage) throws CreateStockException;
}
