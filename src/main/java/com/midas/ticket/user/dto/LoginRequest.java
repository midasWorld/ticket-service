package com.midas.ticket.user.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {

	@Schema(description = "로그인 이메일", required = true)
	private String principal;

	@Schema(description = "로그인 비밀번호", required = true)
	private String credentials;

	public LoginRequest() {
	}

	public LoginRequest(String principal, String credentials) {
		this.principal = principal;
		this.credentials = credentials;
	}

	public String getPrincipal() {
		return principal;
	}

	public String getCredentials() {
		return credentials;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("principal", principal)
			.append("credentials", credentials)
			.toString();
	}
}
