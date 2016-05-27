package com.jpmorgan.interview.stockmarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.interview.stockmarket.exception.ErrorCodes;
import com.jpmorgan.interview.stockmarket.exception.StockServiceException;
import com.jpmorgan.interview.stockmarket.service.IStockService;
import com.jpmorgan.interview.stockmarket.service.StockServiceImpl;

/**
 * TestStockRegistration contains tests to test Stock registration scenarios
 * <p>
 * TODO Externalize Test Data
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class TestStockRegistration {

	IStockService stockService;

	@Before
	public void initTest() {
		stockService = new StockServiceImpl();
	}

	/**
	 * Method testInValidParValue.
	 * 
	 * @throws StockServiceException
	 */
	@Test(expected = StockServiceException.class)
	public void testInValidParValue() throws StockServiceException {
		stockService.registerStock("TSR1", StockType.COMMON, BigDecimal.valueOf(-0.100), BigDecimal.valueOf(0.000),
				0.00);
	}

	@Test
	public void testInValidCommonStockParValueStatusCode() {
		try {
			stockService.registerStock("TSR2", StockType.COMMON, BigDecimal.valueOf(-0.100), BigDecimal.valueOf(0.000),
					0.00);
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_PARVALUE_AMOUNT, e.getErrorCode());
		}
	}

	@Test
	public void testInValidPreferredStockParValueStatusCode() {
		try {
			stockService.registerStock("TSR3", StockType.PREFERRED, BigDecimal.valueOf(-0.100),
					BigDecimal.valueOf(0.000), 0.00);
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_PARVALUE_AMOUNT, e.getErrorCode());
		}
	}

	/**
	 * Method testInValidDividendPercentageValue.
	 * 
	 * @throws StockServiceException
	 */
	@Test(expected = StockServiceException.class)
	public void testInValidDividendPercentageValue() throws StockServiceException {
		stockService.registerStock("TSR4", StockType.PREFERRED, BigDecimal.valueOf(0.100), BigDecimal.valueOf(0.000),
				-1.00);
	}

	@Test
	public void testInValidDividendPercentageValueStatusCode() {
		try {
			stockService.registerStock("TSR5", StockType.PREFERRED, BigDecimal.valueOf(0.100),
					BigDecimal.valueOf(0.000), -1.00);
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_DIVIDEND_PERCENTAGE, e.getErrorCode());
		}
	}

	/**
	 * Method testInValidDividendValue.
	 * 
	 * @throws StockServiceException
	 */
	@Test(expected = StockServiceException.class)
	public void testInValidDividendValue() throws StockServiceException {
		// Pass negative last dividend
		stockService.registerStock("TSR6", StockType.COMMON, BigDecimal.valueOf(0.100), BigDecimal.valueOf(-1.000),
				0.00);
	}

	public void testInValidDividendValueStatusCode() {
		try {
			// Pass negative last dividend
			stockService.registerStock("TSR7", StockType.PREFERRED, BigDecimal.valueOf(0.100),
					BigDecimal.valueOf(-1.000), -1.00);
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.INVALID_LAST_DIVIDEND, e.getErrorCode());
		}
	}

	@Test
	public void testValidStockRegistration() {
		try {
			stockService.registerStock("TSR8", StockType.PREFERRED, BigDecimal.valueOf(0.100),
					BigDecimal.valueOf(1.000), 1.00);
		} catch (StockServiceException e) {
			fail("Stock registration failed");
		}
	}

	@Test
	public void testDuplicateStockRegistration() {
		try {
			stockService.registerStock("TSR9", StockType.PREFERRED, BigDecimal.valueOf(0.100),
					BigDecimal.valueOf(1.000), 1.00);
			stockService.registerStock("TSR9", StockType.PREFERRED, BigDecimal.valueOf(0.100),
					BigDecimal.valueOf(1.000), 1.00);
		} catch (StockServiceException e) {
			assertEquals(ErrorCodes.DUPLICATE_STOCK_REGISTRATION, e.getErrorCode());
		}
	}
	 

}
