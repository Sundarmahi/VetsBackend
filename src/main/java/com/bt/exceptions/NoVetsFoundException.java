package com.bt.exceptions;

public class NoVetsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoVetsFoundException(String message) {
		super(message);
	}
}

