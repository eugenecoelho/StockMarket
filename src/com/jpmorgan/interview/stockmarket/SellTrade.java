package com.jpmorgan.interview.stockmarket;

import com.jpmorgan.interview.stockmarket.exception.CreateTradeException;

/**
 * SellTrade is used to represent a Trade of SELL type !
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class SellTrade extends Trade {

	private Indicator tradeType = Indicator.SELL;

	/**
	 * Constructor for SellTrade.
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
	public SellTrade(StockSymbol stockSymbol, int stockQuantity, Money price) throws CreateTradeException {
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
