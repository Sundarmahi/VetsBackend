package com.bt.exceptions;

public class NoPetsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoPetsFoundException(String message) {
		super(message);
	}

}
