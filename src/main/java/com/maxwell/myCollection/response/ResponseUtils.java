package com.maxwell.myCollection.response;

import org.springframework.http.ResponseEntity;


public class ResponseUtils {
	

	public <T> Response<T> setMessage(Response<T> response, String message, Boolean status) {
		response.setMessage(message);
		response.setStatus(status);
		return response;
	}

	public <T> ResponseEntity<Response<T>> setExceptionMessage(Response<T> response, Exception e) {
		e.printStackTrace();
		response.getErrors().add(e.getCause().toString());
		response.setStatus(false);
		return ResponseEntity.badRequest().body(response);
	}

}
