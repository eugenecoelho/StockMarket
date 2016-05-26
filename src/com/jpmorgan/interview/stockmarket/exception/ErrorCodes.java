package com.jpmorgan.interview.stockmarket.exception;

/**
 * ErrorCodes class holds all the error codes raised during exception
 * 
 * <p>
 * STOCK_NOT_REGISTERED - When stock register doesn't recognize the stock symbol
 * </p>
 * <p>
 * INVALID_DIVIDEND_PERCENTAGE - When negative fixed dividend is passed while
 * registering a preffered stock
 * </p>
 * <p>
 * INVALID_PARVALUE_AMOUNT - When negative parvalue is passed while registering
 * a stock
 * </p>
 * <p>
 * DUPLICATE_STOCK_REGISTRATION - When same stocksymbol is used twice for
 * registering a stock
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public enum ErrorCodes implements IErrorCode {
	STOCK_NOT_REGISTERED(101), DUPLICATE_STOCK_REGISTRATION(102), INVALID_PARVALUE_AMOUNT(
			103), INVALID_DIVIDEND_PERCENTAGE(104), INVALID_STOCK_DIVIDEND_VALUE(
					105), INVALID_STOCK_PRICE(106), INVALID_LAST_DIVIDEND(107), INVALID_TRADE_QUANTITY(108);

	private final int errorCode;

	/**
	 * Constructor for ErrorCodes.
	 * 
	 * @param errorCode
	 *            int
	 */
	private ErrorCodes(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Method getErrorCode.
	 * 
	 * 
	 * @return int * @see
	 *         com.jpmorgan.interview.stockmarket.exception.IErrorCode#
	 *         getErrorCode()
	 */
	@Override
	public int getErrorCode() {
		return errorCode;
	}

}
