package com.bt.exceptions;

public class NoClinicExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NoClinicExistsException(String message) {
		super(message);
	}

}
