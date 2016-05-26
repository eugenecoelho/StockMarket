package com.jpmorgan.interview.stockmarket.exception;

/**
 * StockServiceException is used to wrap exception at the service layer
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public final class StockServiceException extends MainException {

	/**
	 * Constructor for StockServiceException.
	 * 
	 * @param errorCode
	 *            IErrorCode
	 */
	public StockServiceException(IErrorCode errorCode) {
		super(errorCode);
	}

}
