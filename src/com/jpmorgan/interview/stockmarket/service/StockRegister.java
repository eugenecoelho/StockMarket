package com.jpmorgan.interview.stockmarket.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.jpmorgan.interview.stockmarket.Constant;
import com.jpmorgan.interview.stockmarket.IStock;
import com.jpmorgan.interview.stockmarket.ITrade;
import com.jpmorgan.interview.stockmarket.Indicator;
import com.jpmorgan.interview.stockmarket.Money;
import com.jpmorgan.interview.stockmarket.StockFactory;
import com.jpmorgan.interview.stockmarket.StockSymbol;
import com.jpmorgan.interview.stockmarket.StockType;
import com.jpmorgan.interview.stockmarket.TradeFactory;
import com.jpmorgan.interview.stockmarket.exception.CalculationException;
import com.jpmorgan.interview.stockmarket.exception.CreateStockException;
import com.jpmorgan.interview.stockmarket.exception.CreateTradeException;
import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;
import com.jpmorgan.interview.stockmarket.exception.StockRegisterException;

/**
 * StockRegister class maintains a register for the stocks and its trades.
 * 
 * <p>
 * Exposes methods that allow to calculate share index and stock price
 * <p>
 * <p>
 * TODO To rename the class that matches
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class StockRegister {

	private static final Logger LOGGER = Logger.getLogger(StockRegister.class.getName());

	private final Map<StockSymbol, IStock> register = new HashMap<StockSymbol, IStock>();
	private ArrayList<ITrade> trades = new ArrayList<ITrade>();

	/**
	 * TODO Add return status to notify the consumer of success
	 * 
	 * @param stockSymbol
	 * @param stockType
	 * @param parValue
	 * @param fixedDividendPercentage
	 * 
	 * 
	 * 
	 * 
	 * @param lastDividend
	 *            Money
	 * @throws StockRegisterException
	 *             * @see
	 *             com.jpmorgan.interview.stockmarket.exception.ErrorCodes.
	 *             DUPLICATE_STOCK_REGISTRATION * @see
	 *             com.jpmorgan.interview.stockmarket.exception.ErrorCodes.
	 *             INVALID_PARVALUE_AMOUNT * @see
	 *             com.jpmorgan.interview.stockmarket.exception.ErrorCodes.
	 *             INVALID_DIVIDEND_PERCENTAGE
	 */
	public void register(StockSymbol stockSymbol, StockType stockType, Money parValue, Money lastDividend,
			double fixedDividendPercentage) throws StockRegisterException {
		// check for duplicate stocks being registered
		if (lookup(stockSymbol))
			throw new StockRegisterException(ErrorCodes.DUPLICATE_STOCK_REGISTRATION);
		IStock stock;
		try {
			stock = StockFactory.getInstance().createStock(stockSymbol, stockType, parValue, lastDividend,
					fixedDividendPercentage);
		} catch (CreateStockException e) {
			throw new StockRegisterException(e.getErrorCode());
		}
		// allow to register as a new stock
		register.put(stockSymbol, stock);
	}

	/**
	 * 
	 * @param stockSymbol
	 * 
	 * @return false if stock is not registered with StockRegister
	 */
	public boolean lookup(StockSymbol stockSymbol) {
		if (stockSymbol == null)
			return false;
		return register.containsKey(stockSymbol) ? true : false;
	}

	/**
	 * Method getDividendYield.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param stockPrice
	 *            Money
	 * 
	 * 
	 * 
	 * @return BigDecimal * @throws StockRegisterException * @throws
	 *         CalculationException
	 */
	public BigDecimal getDividendYield(StockSymbol stockSymbol, Money stockPrice)
			throws StockRegisterException, CalculationException {
		checkStockRegistration(stockSymbol);
		IStock stock = register.get(stockSymbol);
		return stock.getDividendYield(stockPrice).getValue();
	}

	/**
	 * Method getPERatio.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param stockPrice
	 *            Money
	 * 
	 * 
	 * 
	 * @return BigDecimal * @throws StockRegisterException * @throws
	 *         CalculationException
	 */
	public BigDecimal getPERatio(StockSymbol stockSymbol, Money stockPrice)
			throws StockRegisterException, CalculationException {
		checkStockRegistration(stockSymbol);
		IStock stock = register.get(stockSymbol);
		return stock.getPERatio(stockPrice).getValue();
	}

	/**
	 * setTrade method allows to place a Buy/Sell trade with the Stock Register
	 * 
	 * @param stockSymbol
	 * @param noOfshares
	 * @param price
	 * @param indicator
	 * 
	 * 
	 * 
	 * 
	 * @return boolean * @throws StockRegisterException * @throws
	 *         CreateTradeException * @throws CreateTradeException
	 */
	public boolean setTrade(StockSymbol stockSymbol, int noOfshares, Money price, Indicator indicator)
			throws StockRegisterException, CreateTradeException {
		checkStockRegistration(stockSymbol);
		ITrade trade = TradeFactory.getInstance().createTrade(stockSymbol, noOfshares, price, indicator);
		return trades.add(trade);
	}

	/**
	 * Method checkStockRegistration.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * 
	 * @throws StockRegisterException
	 */
	private void checkStockRegistration(StockSymbol stockSymbol) throws StockRegisterException {
		if (!lookup(stockSymbol))
			throw new StockRegisterException(ErrorCodes.STOCK_NOT_REGISTERED);
	}

	/**
	 * 
	 * @param stockSymbol
	 * 
	 * 
	 * 
	 * 
	 * @param minutes
	 *            int
	 * 
	 * @return BigDecimal * @throws StockRegisterException * @throws Exception
	 */
	public BigDecimal getVolumneWeightedStockPrice(StockSymbol stockSymbol, int minutes) throws StockRegisterException {
		checkStockRegistration(stockSymbol);
		List<ITrade> filteredList = getRecentTrades(stockSymbol, minutes, trades);
		BigDecimal stockPrice = calculateStockPrice(filteredList);
		return stockPrice;
	}

	/**
	 * Method calculateStockPrice.
	 * 
	 * @param filteredList
	 *            List<ITrade>
	 * 
	 * @return BigDecimal
	 */
	private BigDecimal calculateStockPrice(List<ITrade> filteredList) {
		BigDecimal stockPrice = BigDecimal.valueOf(0);
		// Volume weighted Stock Price calculation
		int totalShares = 0;
		BigDecimal totalTradePrice = BigDecimal.valueOf(0.0);
		for (ITrade trade : filteredList) {
			// Trade Price x Quantity
			totalTradePrice = totalTradePrice
					.add(trade.getPrice().getValue().multiply(BigDecimal.valueOf(trade.getStockQuantity())));
			// Total Number of shares
			totalShares += trade.getStockQuantity();
		}
		// stock price calculation
		if (totalShares > 0)
			stockPrice = totalTradePrice.divide(BigDecimal.valueOf(totalShares), Constant.DECIMAL_PRECISION,
					RoundingMode.CEILING);
		return stockPrice;
	}

	/**
	 * Method getRecentTrades.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param minutes
	 *            int
	 * @param trades2
	 *            List<ITrade>
	 * 
	 * @return List<ITrade>
	 */
	private List<ITrade> getRecentTrades(StockSymbol stockSymbol, int minutes, final List<ITrade> trades2) {
		List<ITrade> filteredTrades = null;
		if (minutes > 0) {
			final Calendar dateRange = Calendar.getInstance();
			dateRange.add(Calendar.MINUTE, -minutes);
			filteredTrades = trades2.stream()
					.filter(trade -> trade.getStockSymbol().equals(stockSymbol)
							&& dateRange.getTime().compareTo(trade.getTradetimestamp()) <= 0)
					.collect(Collectors.toList());
		} else {
			filteredTrades = trades2.stream().filter(trade -> trade.getStockSymbol().equals(stockSymbol))
					.collect(Collectors.toList());
		}
		// logger.log(Level.INFO,"Filtered Trades ["+stockSymbol+","+minutes+"]:
		// "+filteredTrades.size());
		return filteredTrades;
	}

	/**
	 * Method getGBCEAllShareIndex.
	 * 
	 * 
	 * @return double * @throws StockRegisterException
	 */
	public double getGBCEAllShareIndex() throws StockRegisterException {
		double allShareIndex = 0.0;

		// Calculate stock price for all stock in the system
		List<BigDecimal> stockPrices = new ArrayList<BigDecimal>();
		for (StockSymbol stockSymbol : register.keySet()) {
			BigDecimal stockPrice = getVolumneWeightedStockPrice(stockSymbol, 0);
			if (stockPrice.compareTo(BigDecimal.valueOf(0)) > 0) {
				stockPrices.add(stockPrice);
			}
		}

		if (stockPrices.size() >= 1) {
			allShareIndex = geometricMean(stockPrices);
		}
		return allShareIndex;
	}

	/**
	 * Method geometricMean.
	 * 
	 * @param stockPrices
	 *            List<BigDecimal>
	 * 
	 * @return double
	 */
	public double geometricMean(List<BigDecimal> stockPrices) {
		int n = stockPrices.size();
		BigDecimal product = BigDecimal.valueOf(1.0d);
		for (int i = 0; i < stockPrices.size(); ++i) {
			product = product.multiply(stockPrices.get(i));
		}
		Double geometricMean = Math.pow(product.doubleValue(), 1.0 / (double) stockPrices.size());
		return geometricMean;
	}

}
