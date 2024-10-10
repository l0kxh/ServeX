package com.mainservice.main.response;

import org.springframework.http.HttpStatus;

public class Response {
	
	private  String responseMessage;
	private HttpStatus httpStatus;
	
	public Response(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
