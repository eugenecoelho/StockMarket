package com.jpmorgan.interview.stockmarket.exception;

/**
 * CreateTradeException to indicate exception when creating trades
 * <p>
 * Check {@link ErrorCodes} for Details
 * </p>
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class CreateTradeException extends MainException {

	/**
	 * Constructor for CreateTradeException.
	 * 
	 * @param errorCode
	 *            IErrorCode
	 */
	public CreateTradeException(IErrorCode errorCode) {
		super(errorCode);
	}

}
