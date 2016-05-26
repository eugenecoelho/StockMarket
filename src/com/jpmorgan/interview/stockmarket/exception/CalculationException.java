package com.jpmorgan.interview.stockmarket.exception;

/**
 * CalculationException to indicate exception while calculating dividends, pe
 * ratio etc
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public class CalculationException extends MainException {

	/**
	 * Constructor for CalculationException.
	 * 
	 * @param errorCode
	 *            IErrorCode
	 */
	public CalculationException(IErrorCode errorCode) {
		super(errorCode);
	}

}
