package com.bt.exceptions;

public class VetAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VetAlreadyExistsException(String message) {
		super(message);
	}

}
