package com.midas.ticket.common;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

public class ApiResponse<T> {

	@Schema(description = "API 요청 처리 결과", required = true)
	private final boolean success;

	@Schema(description = "success가 true라면, API 요청 처리 응답값")
	private final T response;

	@Schema(description = "success가 false라면, API 요청 처리 응답값")
	private final ApiError error;

	public ApiResponse(boolean success, T response, ApiError error) {
		this.success = success;
		this.response = response;
		this.error = error;
	}

	public static <T> ApiResponse<T> OK(T response) {
		return new ApiResponse<>(true, response, null);
	}

	public static ApiResponse<?> ERROR(LocalDateTime timestamp, Throwable throwable) {
		return new ApiResponse<>(false, null, new ApiError(timestamp, throwable));
	}

	public boolean isSuccess() {
		return success;
	}

	public T getResponse() {
		return response;
	}

	public ApiError getError() {
		return error;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("success", success)
			.append("response", response)
			.append("error", error)
			.toString();
	}
}
