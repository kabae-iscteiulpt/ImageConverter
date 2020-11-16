package com.glintt.imageconverter.typemessages;

public enum TypeMessage {

	SUCCESS("000", ""), BAD_REQUEST("001", "BAD_REQUEST"), INTERNAL_SERVER_ERROR("002", "INTERNAL_SERVER_ERROR"),;

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
