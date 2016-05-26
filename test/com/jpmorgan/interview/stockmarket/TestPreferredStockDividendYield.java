package com.jpmorgan.interview.stockmarket;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;
import com.jpmorgan.interview.stockmarket.exception.StockServiceException;
import com.jpmorgan.interview.stockmarket.service.IStockService;
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
public class TestPreferredStockDividendYield {

	IStockService stockService;

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
		stockService.registerStock("XPS", StockType.PREFERRED, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
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
			stockService.registerStock("XEE", StockType.PREFERRED, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
					0.00);
			assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("XEE", BigDecimal.valueOf(-1)));
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_STOCK_PRICE, e.getErrorCode());
		}
	}

	/**
	 * Method testValidPreferredStockDividendYield.
	 * 
	 * @throws StockServiceException
	 */
	@Test
	public void testValidPreferredStockDividendYield() throws StockServiceException {
		// For parvalue 0, fix dividend 0, stock price 0 - Numerator &
		// Denominator Zero
		stockService.registerStock("XYS", StockType.PREFERRED, BigDecimal.valueOf(0.000), BigDecimal.valueOf(0.000),
				0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("XYS", BigDecimal.valueOf(0)));

		// For parvalue 100, fix dividend 0, stock price 0 - Numerator Zero
		stockService.registerStock("CEQ", StockType.PREFERRED, BigDecimal.valueOf(0.100), BigDecimal.valueOf(0.000),
				0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("CEQ", BigDecimal.valueOf(0)));

		// For parvalue 100, fix dividend 0.02, stock price 0 - Denominator Zero
		stockService.registerStock("CXQ", StockType.PREFERRED, BigDecimal.valueOf(0.100), BigDecimal.valueOf(0.000),
				0.00);
		assertEquals(BigDecimal.valueOf(0), stockService.getDividendYield("CXQ", BigDecimal.valueOf(0)));

		// For parvalue 1USD/100 Pennies, fix dividend 0.02, stock price 1.05USD
		stockService.registerStock("XES", StockType.PREFERRED, BigDecimal.valueOf(1.000), BigDecimal.valueOf(0.000),
				0.02);
		assertEquals(BigDecimal.valueOf(0.019047619048),
				stockService.getDividendYield("XES", BigDecimal.valueOf(1.05)));
	}

	@After
	public void closeTest() {

	}

}
