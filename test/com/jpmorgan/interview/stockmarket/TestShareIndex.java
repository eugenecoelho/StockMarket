package com.jpmorgan.interview.stockmarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.interview.stockmarket.exception.StockServiceException;
import com.jpmorgan.interview.stockmarket.service.IStockService;
import com.jpmorgan.interview.stockmarket.service.StockServiceImpl;

/**
 * TestShareIndex tests GBCE All Share Index using the geometric mean of prices
 * for all stocks
 * <p>
 * TODO Externalize Test Data
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class TestShareIndex {

	IStockService stockService;

	@Before
	public void initTest() {
		stockService = new StockServiceImpl();
	}

	/**
	 * Method testAllShareIndex.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testAllShareIndex() throws StockServiceException {
		stockService.registerStock("ASI5", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ASI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.getVolumneWeightedStockPrice("ASI5", 15).compareTo(BigDecimal.valueOf(1.0)) == 0);
		assertEquals(Double.valueOf(1.0), stockService.getAllShareIndex());
	}

	/**
	 * Method testAllShareIndexPrecisoin.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testAllShareIndexPrecisoin() throws StockServiceException {
		stockService.registerStock("ATI5", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);

		stockService.registerStock("ATI6", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);

		assertTrue(stockService.setTrade("ATI5", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 20, Indicator.BUY, BigDecimal.valueOf(0.99)));
		assertTrue(stockService.setTrade("ATI5", 30, Indicator.BUY, BigDecimal.valueOf(0.98)));
		assertTrue(stockService.setTrade("ATI5", 40, Indicator.BUY, BigDecimal.valueOf(0.95)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));

		assertTrue(stockService.setTrade("ATI6", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 20, Indicator.BUY, BigDecimal.valueOf(0.99)));
		assertTrue(stockService.setTrade("ATI6", 30, Indicator.BUY, BigDecimal.valueOf(0.98)));
		assertTrue(stockService.setTrade("ATI6", 40, Indicator.BUY, BigDecimal.valueOf(0.95)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("ATI6", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertEquals(Double.valueOf(0.985263157895), stockService.getAllShareIndex());

	}

}
