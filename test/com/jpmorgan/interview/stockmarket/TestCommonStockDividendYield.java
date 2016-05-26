package com.jpmorgan.interview.stockmarket;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;
import com.jpmorgan.interview.stockmarket.exception.StockServiceException;
import com.jpmorgan.interview.stockmarket.service.StockService;
import com.jpmorgan.interview.stockmarket.service.StockServiceImpl;

/**
 * Tests the methods which calculates the Dividend Yield for a stock
 * 
 * <p>
 * TODO Externalize Test Data
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class TestCommonStockDividendYield {

	StockService stockService;

	@Before
	public void initTest() {
		stockService = new StockServiceImpl();
	}

	@Test
	public void testStockNotRegisteredDividendYieldErrorCode() {
		try {
			stockService.getDividendYield("TEA", BigDecimal.valueOf(0));
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.STOCK_NOT_REGISTERED, e.getErrorCode());
		}
	}

	/**
	 * Method testInvalidStockPrice.
	 * 
	 * @throws StockServiceException
	 */
	@Test(expected = StockServiceException.class)
	public void testInvalidStockPrice() throws StockServiceException {
		// For parvalue 0, fix dividend 0, stock price -1
		stockService.registerStock("XPS", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000), 0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("XPS", BigDecimal.valueOf(-1)));
	}

	/**
	 * Method testInvalidStockPriceErrorCode.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testInvalidStockPriceErrorCode() throws StockServiceException {
		try {
			// For parvalue 0, fix dividend 0, stock price -1
			stockService.registerStock("XEE", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
					0.00);
			assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("XEE", BigDecimal.valueOf(-1)));
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_STOCK_PRICE, e.getErrorCode());
		}
	}

	/**
	 * Method testValidCommonStockDividendYield.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testValidCommonStockDividendYield() throws StockServiceException {
		// For parvalue 0,last dividend 0, fix dividend 0, stock price 0 -
		// Numerator &
		// Denominator Zero
		stockService.registerStock("XYS", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000), 0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("XYS", BigDecimal.valueOf(0)));

		//
		stockService.registerStock("CEQ", StockType.COMMON, BigDecimal.valueOf(1.000), BigDecimal.valueOf(0.000), 0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("CEQ", BigDecimal.valueOf(0)));

		//
		stockService.registerStock("CXQ", StockType.COMMON, BigDecimal.valueOf(1.000), BigDecimal.valueOf(0.08), 0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("CXQ", BigDecimal.valueOf(0)));

		//
		stockService.registerStock("XES", StockType.COMMON, BigDecimal.valueOf(18.000), BigDecimal.valueOf(6.000),
				0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("XES", BigDecimal.valueOf(0.00)));

		//
		stockService.registerStock("ASD", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000), 0.70);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("ASD", BigDecimal.valueOf(0.00)));

		//
		stockService.registerStock("ASP", StockType.COMMON, BigDecimal.valueOf(15.000), BigDecimal.valueOf(0.000),
				0.60);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("ASP", BigDecimal.valueOf(0.00)));

		//
		stockService.registerStock("ASV", StockType.COMMON, BigDecimal.valueOf(1.100), BigDecimal.valueOf(2.500), 0.00);
		assertEquals(BigDecimal.valueOf(2.272727272728),
				stockService.getDividendYield("ASV", BigDecimal.valueOf(1.10)));

		//
		stockService.registerStock("APD", StockType.COMMON, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000), 0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("APD", BigDecimal.valueOf(2.02)));

		//
		stockService.registerStock("ASJ", StockType.COMMON, BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.000), 0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("ASJ", BigDecimal.valueOf(1.02)));

		//
		stockService.registerStock("AXB", StockType.COMMON, BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.230), 1.02);
		assertEquals(BigDecimal.valueOf(0.328571428572),
				stockService.getDividendYield("AXB", BigDecimal.valueOf(0.70)));

		//
		stockService.registerStock("AXN", StockType.COMMON, BigDecimal.valueOf(0.00), BigDecimal.valueOf(0.000), 0.50);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("AXN", BigDecimal.valueOf(0.10)));

		//
		stockService.registerStock("APX", StockType.COMMON, BigDecimal.valueOf(0.60), BigDecimal.valueOf(0.00), 0.20);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("APX", BigDecimal.valueOf(0.70)));

		//
		stockService.registerStock("ANX", StockType.COMMON, BigDecimal.valueOf(0.00), BigDecimal.valueOf(0.13), 0.10);
		assertEquals(BigDecimal.valueOf(0.054166666667),
				stockService.getDividendYield("ANX", BigDecimal.valueOf(2.40)));

		//
		stockService.registerStock("ASA", StockType.COMMON, BigDecimal.valueOf(2.50), BigDecimal.valueOf(0.13), 0.10);
		assertEquals(BigDecimal.valueOf(0.054166666667),
				stockService.getDividendYield("ASA", BigDecimal.valueOf(2.40)));

	}

	@After
	public void closeTest() {

	}

}
