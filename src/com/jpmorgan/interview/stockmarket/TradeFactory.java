package com.jpmorgan.interview.stockmarket;

import com.jpmorgan.interview.stockmarket.exception.CreateTradeException;

/**
 * Used to instantiate objects of type ITrade
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class TradeFactory {

	private static final TradeFactory INSTANCE = new TradeFactory();

	/**
	 * Method createTrade.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param noOfshares
	 *            int
	 * @param price
	 *            Money
	 * @param indicator
	 *            Indicator
	 * 
	 * 
	 * @return ITrade * @throws CreateTradeException
	 */
	public ITrade createTrade(StockSymbol stockSymbol, int noOfshares, Money price, Indicator indicator)
			throws CreateTradeException {
		ITrade trade = null;
		if (indicator.equals(Indicator.BUY)) {
			trade = new BuyTrade(stockSymbol, noOfshares, price);
		}
		if (indicator.equals(Indicator.SELL)) {
			trade = new SellTrade(stockSymbol, noOfshares, price);
		}
		return trade;
	}

	/**
	 * Method getInstance.
	 * 
	 * @return TradeFactory
	 */
	public static TradeFactory getInstance() {
		return INSTANCE;
	}

}
