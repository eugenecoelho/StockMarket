package com.jpmorgan.interview.stockmarket;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.jpmorgan.interview.stockmarket.exception.CreateTradeException;
import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;

/**
 * Trade Class can be inherited by types of trade
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public abstract class Trade implements ITrade {
	private static Date tradeTimeStamp;
	private StockSymbol stockSymbol;
	private int stockQuantity;
	private Indicator indicator;
	private Money price;
	private static Calendar gmtCal;

	static {
		gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	}

	/**
	 * Constructor for Trade.
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
	public Trade(StockSymbol stockSymbol, int stockQuantity, Money price) throws CreateTradeException {
		super();
		if (stockQuantity <= 0)
			throw new CreateTradeException(ErrorCodes.INVALID_TRADE_QUANTITY);
		if (price.isNegative())
			throw new CreateTradeException(ErrorCodes.INVALID_STOCK_PRICE);
		this.stockSymbol = stockSymbol;
		this.stockQuantity = stockQuantity;
		this.price = price;
		Trade.tradeTimeStamp = gmtCal.getTime();
	}

	/**
	 * Method getTradetimestamp.
	 * 
	 * 
	 * @return Date * @see
	 *         com.jpmorgan.interview.stockmarket.ITrade#getTradetimestamp()
	 */
	@Override
	public Date getTradetimestamp() {
		return tradeTimeStamp;
	}

	/**
	 * Method getStockSymbol.
	 * 
	 * 
	 * @return StockSymbol * @see
	 *         com.jpmorgan.interview.stockmarket.ITrade#getStockSymbol()
	 */
	@Override
	public StockSymbol getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * Method getStockQuantity.
	 * 
	 * 
	 * @return int * @see
	 *         com.jpmorgan.interview.stockmarket.ITrade#getStockQuantity()
	 */
	@Override
	public int getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * Method getIndicator.
	 * 
	 * 
	 * @return Indicator * @see
	 *         com.jpmorgan.interview.stockmarket.ITrade#getIndicator()
	 */
	@Override
	public Indicator getIndicator() {
		return indicator;
	}

	/**
	 * Method getPrice.
	 * 
	 * 
	 * @return Money * @see com.jpmorgan.interview.stockmarket.ITrade#getPrice()
	 */
	@Override
	public Money getPrice() {
		return price;
	}

}
