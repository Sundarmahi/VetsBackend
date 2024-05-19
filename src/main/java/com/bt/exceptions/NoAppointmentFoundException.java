package com.bt.exceptions;

public class NoAppointmentFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoAppointmentFoundException(String message) {
		super(message);
	}
}
