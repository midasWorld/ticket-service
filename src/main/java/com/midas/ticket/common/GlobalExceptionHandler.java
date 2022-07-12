package com.midas.ticket.common;

import static com.midas.ticket.common.ApiResponse.ERROR;
import static java.time.LocalDateTime.now;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<ApiResponse<?>> newResponse(Throwable throwable, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<>(ERROR(now(), throwable), headers, status);
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<?> handleBadRequest(Exception e) {
		log.debug("Bad request exception occurred: {}", e.getMessage(), e);
		return newResponse(e, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ResponseEntity<?> handleAllException(Exception e) {
		log.error("Unexpected exception occurred: {}", e.getMessage(), e);
		return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
