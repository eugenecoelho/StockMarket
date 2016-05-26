package com.jpmorgan.interview.stockmarket;

import com.jpmorgan.interview.stockmarket.exception.CreateStockException;

/**
 * StockFactory helps to create stocks
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public final class StockFactory implements IStockFactory {

	private static final StockFactory INSTANCE = new StockFactory();

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
	 * 
	 * @return IStock * @throws CreateStockException * @see
	 *         com.jpmorgan.interview.stockmarket.IStockFactory#createStock(
	 *         StockSymbol, StockType, Money, Money, double)
	 */
	public IStock createStock(StockSymbol stockSymbol, StockType stockType, Money parValue, Money lastDividend,
			double fixedDividendPercentage) throws CreateStockException {
		IStock newStock = null;
		if (stockType.equals(StockType.COMMON)) {
			newStock = new CommonStock(stockSymbol, parValue, lastDividend);
		}
		if (stockType.equals(StockType.PREFERRED)) {
			newStock = new PreferredStock(stockSymbol, parValue, lastDividend, fixedDividendPercentage);
		}
		return newStock;
	}

	/**
	 * Method getInstance.
	 * 
	 * @return StockFactory
	 */
	public static StockFactory getInstance() {
		return INSTANCE;
	}

}
