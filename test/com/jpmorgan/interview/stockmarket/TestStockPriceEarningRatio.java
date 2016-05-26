package com.jpmorgan.interview.stockmarket;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.interview.stockmarket.exception.StockServiceException;
import com.jpmorgan.interview.stockmarket.service.IStockService;
import com.jpmorgan.interview.stockmarket.service.StockServiceImpl;

/**
 * TestStockPriceEarningRatio contains test to test P/E ratio calculation
 * <p>
 * TODO Externalize Test Data
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class TestStockPriceEarningRatio {

	IStockService stockService;

	@Before
	public void initTest() {
		stockService = new StockServiceImpl();
	}

	/**
	 * Method testCommonStockPriceEarningRatio.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testCommonStockPriceEarningRatio() throws StockServiceException {
		// Common Stock - Zero Price / Zero Dividend
		stockService.registerStock("XCS", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000), 0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getPERatio("XCS", BigDecimal.valueOf(0)));

		// Common Stock - Increase in Stock Price - 2.51 / 0.13 = 19.307692
		stockService.registerStock("LSS", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.130), 0.00);
		assertEquals(BigDecimal.valueOf(19.307692307693), stockService.getPERatio("LSS", BigDecimal.valueOf(2.51)));

	}

	/**
	 * Method testPreferredStockPriceEarningRatio.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testPreferredStockPriceEarningRatio() throws StockServiceException {
		// Preferred Stock - Zero Price / Zero Dividend
		stockService.registerStock("XLS", StockType.PREFERRED, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getPERatio("XLS", BigDecimal.valueOf(0)));

		// Preferred Stock - Decrease in Stock Price - 0.09 / 0.08 = 1.125
		stockService.registerStock("PLS", StockType.PREFERRED, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.080),
				0.00);
		assertEquals(BigDecimal.valueOf(1.125),
				stockService.getPERatio("PLS", BigDecimal.valueOf(0.09)).setScale(3, RoundingMode.CEILING));
	}

}
