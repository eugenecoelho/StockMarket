package com.jpmorgan.interview.stockmarket.service;

import java.math.BigDecimal;

import com.jpmorgan.interview.stockmarket.Indicator;
import com.jpmorgan.interview.stockmarket.StockType;
import com.jpmorgan.interview.stockmarket.exception.StockServiceException;

/**
 * StockService
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public interface IStockService {

	/**
	 * Registers a stock with the stock register
	 * 
	 * <p>
	 * Once stock is registered, supported operations can be invoked
	 * {@link #getDividendYield(String, BigDecimal)}
	 * {@link #getPERatio(String, BigDecimal) </p>
	 * 
	 * @param stockSymbol
	 * @param stockType
	 * @param parValue
	 * @param lastDividend
	 * @param fixedDividendPercentage
	 * 
	 * @throws StockServiceException
	 */
	public void registerStock(String stockSymbol, StockType stockType, BigDecimal parValue, BigDecimal lastDividend,
			double fixedDividendPercentage) throws StockServiceException;

	/**
	 * Calculates dividend yield based on the stock type
	 * 
	 * <p>
	 * Stock needs to be registered before calling calculate dividend
	 * {@link #registerStock()}
	 * <p>
	 * <p>
	 * Stock Symbol is a stockSymbol that is used while registering the stock
	 * {@link #registerStock()}
	 * <p>
	 * <p>
	 * Stock Price is in USD for e.g 100Pennies is 1USD, 60Pennies is 0.60USD
	 * <p>
	 * 
	 * @param stockSymbol
	 *            - stockSymbol used while registering stock
	 * @param stockPrice
	 * 
	 * 
	 * 
	 * 
	 * @return BigDecimal * @throws StockServiceException
	 *         {@link ErrorCodes#STOCK_NOT_REGISTERED}
	 */
	public BigDecimal getDividendYield(String stockSymbol, BigDecimal stockPrice) throws StockServiceException;

	/**
	 * Calculates PE ratio
	 * 
	 * <p>
	 * Stock needs to be registered before calling calculate pe ratio
	 * {@link #registerStock()}
	 * <p>
	 * 
	 * @param stockSymbol
	 *            - stockSymbol used while registering stock
	 * @param stockPrice
	 * 
	 * 
	 * 
	 * @return BigDecimal * @throws StockServiceException
	 */
	public BigDecimal getPERatio(String stockSymbol, BigDecimal stockPrice) throws StockServiceException;

	/**
	 * setTrade Method to record a trade for a particular stock
	 * 
	 * <p>
	 * Stock needs to be registered before calling calculate pe ratio
	 * {@link #registerStock()}
	 * <p>
	 * <p>
	 * Timestamp is generated on the server
	 * </P>
	 * 
	 * @param stockSymbol
	 *            - stockSymbol used while registering stock
	 * @param noOfshares
	 * @param indicator
	 * @param price
	 * 
	 * 
	 * @return boolean * @throws StockServiceException
	 */
	public boolean setTrade(String stockSymbol, int noOfshares, Indicator indicator, BigDecimal price)
			throws StockServiceException;

	/**
	 * getVolumneWeightedStockPrice Method to provide VolumneWeightedStockPrice
	 * 
	 * @param stockSymbol
	 *            String
	 * @param minutes
	 *            int
	 * 
	 * 
	 * @return BigDecimal * @throws StockServiceException
	 */
	public BigDecimal getVolumneWeightedStockPrice(String stockSymbol, int minutes) throws StockServiceException;

	/**
	 * getAllShareIndex Method to provide getAllShareIndex using geometric mean
	 * 
	 * 
	 * @return Double * @throws StockServiceException
	 */
	public Double getAllShareIndex() throws StockServiceException;

}
