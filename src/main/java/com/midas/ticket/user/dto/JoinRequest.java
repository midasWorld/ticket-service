package com.midas.ticket.user.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

public class JoinRequest {

	@Schema(description = "로그인 이메일", required = true)
	private String principal;

	@Schema(description = "로그인 비밀번호", required = true)
	private String credentials;

	@Schema(description = "이름", required = true)
	private String name;

	public JoinRequest() {
	}

	public JoinRequest(String principal, String credentials, String name) {
		this.principal = principal;
		this.credentials = credentials;
		this.name = name;
	}

	public String getPrincipal() {
		return principal;
	}

	public String getCredentials() {
		return credentials;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("principal", principal)
			.append("credentials", credentials)
			.append("name", name)
			.toString();
	}
}
