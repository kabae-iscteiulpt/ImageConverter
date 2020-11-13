package com.glintt.imageconverter.typemessages;

public enum TypeMessage {

	SUCCESS("000", ""), NOT_FOUND("001", "NOT_FOUND"), INTERNAL_SERVER_ERROR("002", "INTERNAL_SERVER_ERROR"),
	BAD_REQUEST("003", "BAD_REQUEST");

	private String code;
	private String message;

	private TypeMessage(String code, String message) {

		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
