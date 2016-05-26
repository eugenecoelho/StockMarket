package com.jpmorgan.interview.stockmarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;
import com.jpmorgan.interview.stockmarket.exception.StockServiceException;
import com.jpmorgan.interview.stockmarket.service.IStockService;
import com.jpmorgan.interview.stockmarket.service.StockServiceImpl;

/**
 * Tests for logging a Stock Trade
 * <p>
 * TODO Externalize Test Data
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class TestStockTrade {

	IStockService stockService;

	@Before
	public void initTest() {
		stockService = new StockServiceImpl();
	}

	/**
	 * Method testStockNotRegisteredTrade.
	 * 
	 * @throws StockServiceException
	 */
	@Test(expected = StockServiceException.class)
	public void testStockNotRegisteredTrade() throws StockServiceException {
		// Add with timestamp, quantity of shares, buy or sell indicator and
		// traded price
		stockService.setTrade("TEA", 10, Indicator.BUY, BigDecimal.valueOf(1.00));
	}

	// Pass Invalid Stock Symbol
	@Test
	public void testStockNotRegisteredSetTradeErrorCode() {
		try {
			stockService.setTrade("STI1", 10, Indicator.BUY, BigDecimal.valueOf(1.00));
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.STOCK_NOT_REGISTERED, e.getErrorCode());
		}
	}

	// Pass Invalid Quantity of Shares
	@Test
	public void testSetInvalidNegativeQuantity() {
		try {
			stockService.registerStock("STI2", StockType.PREFERRED, BigDecimal.valueOf(0.000),
					BigDecimal.valueOf(0.000), 0.00);
			stockService.setTrade("STI2", -10, Indicator.BUY, BigDecimal.valueOf(1.00));
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_TRADE_QUANTITY, e.getErrorCode());
		}
	}

	// Pass Invalid Price
	@Test
	public void testSetInvalidTradePrice() {
		try {
			stockService.registerStock("STI3", StockType.PREFERRED, BigDecimal.valueOf(0.000),
					BigDecimal.valueOf(0.000), 0.00);
			stockService.setTrade("STI3", 10, Indicator.BUY, BigDecimal.valueOf(-1.00));
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_STOCK_PRICE, e.getErrorCode());
		}
	}

	//

	/**
	 * Method testPreferredStockRecordTrade.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testPreferredStockRecordTrade() throws StockServiceException {
		stockService.registerStock("STI4", StockType.PREFERRED, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		assertTrue(stockService.setTrade("STI4", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI4", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
	}

	/**
	 * Method testCommonStockRecordTrade.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testCommonStockRecordTrade() throws StockServiceException {
		stockService.registerStock("STI5", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		assertTrue(stockService.setTrade("STI5", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
	}

}
