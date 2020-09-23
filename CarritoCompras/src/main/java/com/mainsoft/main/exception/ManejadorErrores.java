package com.mainsoft.main.exception;

public class ManejadorErrores {
	
	private String HttpStatus;
	private String Message;
	private String Code;
	private String BackendMessage;
	
	public ManejadorErrores(String httpStatus, String message, String code, String backendMessage) {
		HttpStatus = httpStatus;
		Message = message;
		Code = code;
		BackendMessage = backendMessage;
	}

	public String getHttpStatus() {
		return HttpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		HttpStatus = httpStatus;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getBackendMessage() {
		return BackendMessage;
	}

	public void setBackendMessage(String backendMessage) {
		BackendMessage = backendMessage;
	}
	
}
