package com.midas.ticket.common;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

public class ApiError {

	@Schema(description = "오류 발생 시간", required = true)
	private final LocalDateTime timestamp;

	@Schema(description = "오류 메시지", required = true)
	private final String message;

	public ApiError(LocalDateTime timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
	}

	public ApiError(LocalDateTime timestamp, Throwable throwable) {
		this(timestamp, throwable.getMessage());
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("timestamp", timestamp)
			.append("message", message)
			.toString();
	}
}
