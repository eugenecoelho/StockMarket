package com.jpmorgan.interview.stockmarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.interview.stockmarket.exception.StockServiceException;
import com.jpmorgan.interview.stockmarket.service.StockService;
import com.jpmorgan.interview.stockmarket.service.StockServiceImpl;

/**
 * TestVolumeWeightedStockPrice contains tests to test Volume Weighted Stock
 * Price
 * <p>
 * TODO Externalize Test Data
 * </p>
 * 
 * @author Eugene Coelho
 * @version $Revision: 1.0 $
 */
public class TestVolumeWeightedStockPrice {

	StockService stockService;

	@Before
	public void initTest() {
		stockService = new StockServiceImpl();
	}

	/**
	 * Method testVolumeWeightedStockPrice.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testVolumeWeightedStockPrice() throws StockServiceException {
		stockService.registerStock("STI4", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		stockService.registerStock("STI5", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		assertTrue(stockService.setTrade("STI4", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI4", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI4", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.getVolumneWeightedStockPrice("STI5", 15).compareTo(BigDecimal.valueOf(1.0)) == 0);
	}

	/**
	 * Method testVolumeWeightedStockPricePrecision.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testVolumeWeightedStockPricePrecision() throws StockServiceException {
		stockService.registerStock("STI4", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		stockService.registerStock("STI5", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		assertTrue(stockService.setTrade("STI5", 10, Indicator.BUY, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 20, Indicator.BUY, BigDecimal.valueOf(0.99)));
		assertTrue(stockService.setTrade("STI5", 30, Indicator.BUY, BigDecimal.valueOf(0.98)));
		assertTrue(stockService.setTrade("STI5", 40, Indicator.BUY, BigDecimal.valueOf(0.95)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertTrue(stockService.setTrade("STI5", 10, Indicator.SELL, BigDecimal.valueOf(1.00)));
		assertEquals(BigDecimal.valueOf(0.985263157895), stockService.getVolumneWeightedStockPrice("STI5", 15));
	}

}
