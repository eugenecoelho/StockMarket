package com.jpmorgan.interview.stockmarket.exception;

/**
 * Main Exception class which is used by all the exceptions
 * 
 * @author Eugene Coelho
 *
 * @version $Revision: 1.0 $
 */
public abstract class MainException extends Exception {
	private IErrorCode errorCode;

	/**
	 * Method getErrorCode.
	 * 
	 * @return IErrorCode
	 */
	public IErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * Constructor for MainException.
	 * 
	 * @param errorCode
	 *            IErrorCode
	 */
	MainException(IErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
}
