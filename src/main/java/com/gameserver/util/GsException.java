package com.gameserver.util;

public class GsException extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	private String message;

	public GsException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public GsException(ErrorCode errorCode, String message) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
