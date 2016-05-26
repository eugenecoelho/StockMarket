package com.jpmorgan.interview.stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.jpmorgan.interview.stockmarket.exception.CalculationException;
import com.jpmorgan.interview.stockmarket.exception.CreateStockException;
import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;

/**
 * PreferredStock is a type of stock that has fixed dividend
 * 
 * <p>
 * Implements Dividend Yield and Price Earning Ratio Calculation for Preferred
 * Stock
 * </p>
 * <p>
 * Last dividend that is paid is indicated by {@link AbstractStock#lastDividend}
 * <p>
 * Fixed dividend percentage value is indicated by
 * {@link #fixedDividendPercentage}
 * <p>
 * Stock Type is indicated by Enum called stockType
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public final class PreferredStock extends AbstractStock {

	public final StockType stockType = StockType.PREFERRED;

	private double fixedDividendPercentage;

	/**
	 * Constructor for PreferredStock.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param parValue
	 *            Money
	 * @param lastDividend
	 *            Money
	 * @param fixedDividendPercentage
	 *            double
	 * 
	 * @throws CreateStockException
	 */
	public PreferredStock(StockSymbol stockSymbol, Money parValue, Money lastDividend, double fixedDividendPercentage)
			throws CreateStockException {
		if (parValue.isNegative())
			throw new CreateStockException(ErrorCodes.INVALID_PARVALUE_AMOUNT);
		if (fixedDividendPercentage < 0)
			throw new CreateStockException(ErrorCodes.INVALID_DIVIDEND_PERCENTAGE);
		setFixedDividendPercentage(fixedDividendPercentage);
		setStockSymbol(stockSymbol);
		setParValue(parValue);
		setLastDividend(lastDividend);
	}

	/**
	 * Method getFixedDividendPercentage.
	 * 
	 * 
	 * @return double * @see com.jpmorgan.interview.stockmarket.IStock#
	 *         getFixedDividendPercentage()
	 */
	@Override
	public double getFixedDividendPercentage() {
		return fixedDividendPercentage;
	}

	/**
	 * Method setFixedDividendPercentage.
	 * 
	 * @param fixedDividendPercentage
	 *            double
	 */
	public void setFixedDividendPercentage(double fixedDividendPercentage) {
		this.fixedDividendPercentage = fixedDividendPercentage;
	}

	/**
	 * Method getStockType.
	 * 
	 * 
	 * @return StockType * @see
	 *         com.jpmorgan.interview.stockmarket.IStock#getStockType()
	 */
	@Override
	public StockType getStockType() {
		return stockType;
	}

	/**
	 * Calculates Dividend Yield by (Fixed Dividend * Par Value)/ Stock Price
	 * 
	 * @param stockPrice
	 *            given stockPrice for the stock
	 * 
	 * 
	 * @see com.jpmorgan.interview.stockmarket.IStock#getDividendYield(Money)
	 * @return dividend yield value in form of Money {@see Money}
	 *         {@see AbstractStock#getParValue()}
	 *         {@see AbstractStock#getFixedDividendPercentage()} * @throws
	 *         CalculationException * @throws CalculationException
	 * @see com.jpmorgan.interview.stockmarket.IStock#getDividendYield(Money)
	 */
	@Override
	public Money getDividendYield(final Money stockPrice) throws CalculationException {
		if (stockPrice.isNegative())
			throw new CalculationException(ErrorCodes.INVALID_STOCK_PRICE);
		BigDecimal dividendPerShare = getParValue().getValue().multiply(BigDecimal.valueOf(fixedDividendPercentage));
		BigDecimal dividendYield = BigDecimal.valueOf(0);
		if (isGreaterThanZero(dividendPerShare) && isGreaterThanZero(stockPrice.getValue())) {
			dividendYield = dividendPerShare.divide(stockPrice.getValue(), Constant.DECIMAL_PRECISION,
					RoundingMode.CEILING);
		}
		return Money.valueOf(dividendYield);
	}

}
