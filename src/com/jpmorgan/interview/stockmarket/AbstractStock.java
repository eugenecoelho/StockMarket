package com.jpmorgan.interview.stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.jpmorgan.interview.stockmarket.exception.CalculationException;
import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;

/**
 * AbstractStock provides default implementation of the IStock interface
 * 
 * <p>
 * More specific types of stock should extend AbstractStock.
 * </p>
 * <p>
 * Decimal places for stock calculation are restricted to 12 decimal.
 * </p>
 * <p>
 * Stock Price and Dividend paid are of type Money.
 * </p>
 * <p>
 * TODO - Restrict the stock symbol to certain chars
 * </p>
 * <p>
 * TODO - Allow last dividend paid by the stock to be modified
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public abstract class AbstractStock implements IStock {

	private StockSymbol stockSymbol;
	private Money parValue;
	private Money lastDividend;

	/**
	 * Method getFixedDividendPercentage.
	 * 
	 * 
	 * @return double * @see com.jpmorgan.interview.stockmarket.IStock#
	 *         getFixedDividendPercentage()
	 */
	public abstract double getFixedDividendPercentage();

	/**
	 * Method getStockType.
	 * 
	 * 
	 * @return StockType * @see
	 *         com.jpmorgan.interview.stockmarket.IStock#getStockType()
	 */
	public abstract StockType getStockType();

	/**
	 * Method getDividendYield.
	 * 
	 * @param stockPrice
	 *            Money
	 * 
	 * 
	 * 
	 * @return Money * @throws CalculationException * @see
	 *         com.jpmorgan.interview.stockmarket.IStock#getDividendYield(Money)
	 */
	public abstract Money getDividendYield(Money stockPrice) throws CalculationException;

	/**
	 * @param stockSymbol
	 */
	void setStockSymbol(final StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * @param parValue
	 */
	void setParValue(final Money parValue) {
		this.parValue = parValue;
	}

	/**
	 * @param lastDividend
	 */
	void setLastDividend(final Money lastDividend) {
		this.lastDividend = lastDividend;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jpmorgan.interview.stockmarket.IStock#getStockSymbol()
	 */
	@Override
	public StockSymbol getStockSymbol() {
		return stockSymbol;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jpmorgan.interview.stockmarket.IStock#getParValue()
	 */
	@Override
	public Money getParValue() {
		return parValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jpmorgan.interview.stockmarket.IStock#getLastDividend()
	 */
	@Override
	public Money getLastDividend() {
		return lastDividend;
	}

	/**
	 * Method isGreaterThanZero.
	 * 
	 * @param value
	 *            BigDecimal
	 * 
	 * @return boolean
	 */
	boolean isGreaterThanZero(final BigDecimal value) {
		return value.doubleValue() > 0;
	}

	/**
	 * Calculates Price/Earning (P/E) ratio by Stock Price/Last Dividend
	 * 
	 * @param stockPrice
	 *            given stockPrice for the stock
	 * 
	 * 
	 * @see com.jpmorgan.interview.stockmarket.IStock#getPERatio(Money)
	 * @return dividend yield value in form of Money {@see Money}
	 *         {@see AbstractStock#getLastDividend()} * @throws
	 *         CalculationException * @throws CalculationException
	 * @see com.jpmorgan.interview.stockmarket.IStock#getPERatio(Money)
	 */
	@Override
	public Money getPERatio(final Money stockPrice) throws CalculationException {
		if (stockPrice.isNegative()) {
			throw new CalculationException(ErrorCodes.INVALID_STOCK_PRICE);
		}
		BigDecimal peRatio = BigDecimal.valueOf(0);
		if (isGreaterThanZero(getLastDividend().getValue()) && isGreaterThanZero(stockPrice.getValue())) {
			peRatio = stockPrice.getValue().divide(getLastDividend().getValue(), Constant.DECIMAL_PRECISION,
					RoundingMode.CEILING);
		}
		return Money.valueOf(peRatio);
	}

}
