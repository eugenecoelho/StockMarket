package com.jpmorgan.interview.stockmarket.service;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jpmorgan.interview.stockmarket.Indicator;
import com.jpmorgan.interview.stockmarket.Money;
import com.jpmorgan.interview.stockmarket.StockType;
import com.jpmorgan.interview.stockmarket.exception.CalculationException;
import com.jpmorgan.interview.stockmarket.exception.CreateTradeException;
import com.jpmorgan.interview.stockmarket.exception.StockRegisterException;
import com.jpmorgan.interview.stockmarket.exception.StockServiceException;

/**
 * Provides the implementation for stock service API
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class StockServiceImpl implements IStockService {
	
	/**
	 * Logger for exceptions
	 */
	private static final Logger LOGGER = Logger.getLogger(StockServiceImpl.class.getName());
	
	private final StockRegister stockRegister = new StockRegister();
	private final StockSymbols stockSymbols = new StockSymbols();

	/**
	 * Method registerStock.
	 * 
	 * @param stockSymbol
	 *            String
	 * @param stockType
	 *            StockType
	 * @param parValue
	 *            BigDecimal
	 * @param lastDividend
	 *            BigDecimal
	 * @param fixedDividendPercentage
	 *            double
	 * 
	 * 
	 * @throws StockServiceException
	 *             * @see
	 *             com.jpmorgan.interview.stockmarket.service.IStockService#
	 *             registerStock(String, StockType, BigDecimal, BigDecimal,
	 *             double)
	 */
	@Override
	public void registerStock(String stockSymbol, StockType stockType, BigDecimal parValue, BigDecimal lastDividend,
			double fixedDividendPercentage) throws StockServiceException {
		try {
			stockRegister.register(stockSymbols.lookup(stockSymbol), stockType, Money.valueOf(parValue),
					Money.valueOf(lastDividend), fixedDividendPercentage);
		} catch (StockRegisterException e) {
			LOGGER.log(Level.FINE,"Stock Registeration exception ["+stockSymbol+" "+stockType+"  "+parValue+"  "+lastDividend+" "+fixedDividendPercentage+"]:"+e.getErrorCode());  
			throw new StockServiceException(e.getErrorCode());
		}
	}

	/**
	 * Method getDividendYield.
	 * 
	 * @param stockSymbol
	 *            String
	 * @param stockPrice
	 *            BigDecimal
	 * 
	 * 
	 * 
	 * @return BigDecimal * @throws StockServiceException * @see
	 *         com.jpmorgan.interview.stockmarket.service.StockService#
	 *         getDividendYield(String, BigDecimal)
	 */
	@Override
	public BigDecimal getDividendYield(String stockSymbol, BigDecimal stockPrice) throws StockServiceException {
		try {
			return stockRegister.getDividendYield(stockSymbols.lookup(stockSymbol), Money.valueOf(stockPrice));
		} catch (StockRegisterException | CalculationException e) {
			LOGGER.log(Level.FINE,"Get DividendYield exception ["+stockSymbol+" "+stockPrice+"]:"+e.getErrorCode());
			throw new StockServiceException(e.getErrorCode());
		}
	}

	/**
	 * Method getPERatio.
	 * 
	 * @param stockSymbol
	 *            String
	 * @param stockPrice
	 *            BigDecimal
	 * 
	 * 
	 * 
	 * @return BigDecimal * @throws StockServiceException * @see
	 *         com.jpmorgan.interview.stockmarket.service.StockService#
	 *         getPERatio(String, BigDecimal)
	 */
	@Override
	public BigDecimal getPERatio(String stockSymbol, BigDecimal stockPrice) throws StockServiceException {
		try {
			return stockRegister.getPERatio(stockSymbols.lookup(stockSymbol), Money.valueOf(stockPrice));
		} catch (StockRegisterException | CalculationException e) {
			LOGGER.log(Level.FINE,"Get PE Ratio exception ["+stockSymbol+" "+stockPrice+"]:"+e.getErrorCode());
			throw new StockServiceException(e.getErrorCode());
		}
	}

	/**
	 * Method setTrade.
	 * 
	 * @param stockSymbol
	 *            String
	 * @param noOfshares
	 *            int
	 * @param indicator
	 *            Indicator
	 * @param price
	 *            BigDecimal
	 * 
	 * 
	 * 
	 * @return boolean * @throws StockServiceException * @see
	 *         com.jpmorgan.interview.stockmarket.service.StockService#setTrade(
	 *         String, int, Indicator, BigDecimal)
	 */
	@Override
	public boolean setTrade(String stockSymbol, int noOfshares, Indicator indicator, BigDecimal price)
			throws StockServiceException {
		try {
			return stockRegister.setTrade(stockSymbols.lookup(stockSymbol), noOfshares, Money.valueOf(price),
					indicator);
		} catch (StockRegisterException | CreateTradeException e ) {
			LOGGER.log(Level.FINE,"set trade exception ["+stockSymbol+" "+noOfshares+" "+indicator+" "+price+"]:"+e.getErrorCode());
			throw new StockServiceException(e.getErrorCode());
		}
	}

	/**
	 * Method getVolumneWeightedStockPrice.
	 * 
	 * @param stockSymbol
	 *            String
	 * @param minutes
	 *            int
	 * 
	 * 
	 * 
	 * @return BigDecimal * @throws StockServiceException * @see
	 *         com.jpmorgan.interview.stockmarket.service.StockService#
	 *         getVolumneWeightedStockPrice(String, int)
	 */
	@Override
	public BigDecimal getVolumneWeightedStockPrice(String stockSymbol, int minutes) throws StockServiceException {
		try {
			return stockRegister.getVolumneWeightedStockPrice(stockSymbols.lookup(stockSymbol), minutes);
		} catch (StockRegisterException e) {
			LOGGER.log(Level.FINE,"Get Volumne Weighted Stock Price Exception ["+stockSymbol+" "+minutes+"]:"+e.getErrorCode());
			throw new StockServiceException(e.getErrorCode());
		}
	}

	/**
	 * Method getAllShareIndex.
	 * 
	 * 
	 * 
	 * @return Double * @throws StockServiceException * @see
	 *         com.jpmorgan.interview.stockmarket.service.StockService#
	 *         getAllShareIndex()
	 */
	@Override
	public Double getAllShareIndex() throws StockServiceException {
		try {
			return stockRegister.getGBCEAllShareIndex();
		} catch (StockRegisterException e) {
			LOGGER.log(Level.FINE,"Get All Share Index Exception"+e.getErrorCode());
			throw new StockServiceException(e.getErrorCode());
		}
	}

}
