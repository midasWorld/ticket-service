package com.midas.ticket.user.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

public class MemberResponse {

	@Schema(description = "사용자 ID", required = true)
	private final Long id;

	@Schema(description = "사용자 이름", required = true)
	private final String name;

	public MemberResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("name", name)
			.toString();
	}
}
