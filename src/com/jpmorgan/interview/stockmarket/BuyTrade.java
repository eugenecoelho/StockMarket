package com.jpmorgan.interview.stockmarket;

import com.jpmorgan.interview.stockmarket.exception.CreateTradeException;

/**
 * BuyTrade is used to represent a Trade of BUY type !
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class BuyTrade extends Trade {

	private Indicator tradeType = Indicator.BUY;

	/**
	 * Constructor for BuyTrade.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param stockQuantity
	 *            int
	 * @param price
	 *            Money
	 * 
	 * @throws CreateTradeException
	 */
	public BuyTrade(StockSymbol stockSymbol, int stockQuantity, Money price) throws CreateTradeException {
		super(stockSymbol, stockQuantity, price);
	}

	/**
	 * Method getTradeType.
	 * 
	 * @return Indicator
	 */
	public Indicator getTradeType() {
		return tradeType;
	}

}
