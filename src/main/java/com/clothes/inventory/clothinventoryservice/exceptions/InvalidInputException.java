package com.clothes.inventory.clothinventoryservice.exceptions;

public class InvalidInputException extends ApplicationException {

	private static final long serialVersionUID = 1678422529490927702L;

	public InvalidInputException(String msg, Throwable e) {
		super(msg, e);
	}

	public InvalidInputException(String msg) {
		super(msg);
	}

	public InvalidInputException(Throwable e) {
		super(e);
	}

}
