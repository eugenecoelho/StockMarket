package com.jpmorgan.interview.stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.jpmorgan.interview.stockmarket.exception.CalculationException;
import com.jpmorgan.interview.stockmarket.exception.CreateStockException;
import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;

/**
 * Common Stock extends AbstractStock and is a type of stock with no fixed
 * dividend.
 * 
 * <p>
 * Implements Dividend Yield and Price Earning Ratio Calculation for Common
 * Stock
 * </p>
 * <p>
 * Assumption Common Stock don't have (0%) a fixed dividend percentage
 * </p>
 * <p>
 * Stock Type is indicated by Enum called stockType .
 * </p>
 * 
 * @author Eugene Coelho {@See AbstractStock}
 *
 * @version $Revision: 1.0 $
 */
public class CommonStock extends AbstractStock {

	public final StockType stockType = StockType.COMMON;

	/**
	 * Constructor for CommonStock.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param parValue
	 *            Money
	 * @param lastDividend
	 *            Money
	 * 
	 * @throws CreateStockException
	 */
	public CommonStock(StockSymbol stockSymbol, Money parValue, Money lastDividend) throws CreateStockException {
		if (parValue.isNegative())
			throw new CreateStockException(ErrorCodes.INVALID_PARVALUE_AMOUNT);
		if (lastDividend.isNegative())
			throw new CreateStockException(ErrorCodes.INVALID_DIVIDEND_PERCENTAGE);
		setStockSymbol(stockSymbol);
		setParValue(parValue);
		setLastDividend(lastDividend);
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
	 * Method getFixedDividendPercentage.
	 * 
	 * 
	 * @return double * @see com.jpmorgan.interview.stockmarket.IStock#
	 *         getFixedDividendPercentage()
	 */
	@Override
	public double getFixedDividendPercentage() {
		return 0;
	}

	/**
	 * Calculates Dividend Yield for Common Stock - Last Dividend/Stock Price
	 * 
	 *
	 * 
	 * @since 1.0
	 * @param stockPrice
	 *            Money
	 * 
	 * @see com.jpmorgan.interview.stockmarket.IStock#getDividendYield(Money)
	 * @return dividend yield value in form of Money {@see Money}
	 *         {@see CommonStock#getLastDividend()}
	 *         {@see Abstract#getLastDividend()} * @throws CalculationException
	 *         * @throws CalculationException
	 * @see com.jpmorgan.interview.stockmarket.IStock#getDividendYield(Money)
	 */
	@Override
	public Money getDividendYield(Money stockPrice) throws CalculationException {
		if (stockPrice.isNegative())
			throw new CalculationException(ErrorCodes.INVALID_STOCK_PRICE);
		BigDecimal dividendYield = BigDecimal.valueOf(0);
		if (isGreaterThanZero(getLastDividend().getValue()) && isGreaterThanZero(stockPrice.getValue())) {
			dividendYield = getLastDividend().getValue().divide(stockPrice.getValue(), Constant.DECIMAL_PRECISION,
					RoundingMode.CEILING);
		}
		return Money.valueOf(dividendYield);
	}

}
