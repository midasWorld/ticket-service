package com.midas.ticket.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Builder;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@NotBlank
	@Column(unique = true)
	private String email;

	@NotBlank
	@Size(min = 4)
	private String password;

	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	protected Member() {/* no-op */}

	@Builder
	public Member(String email, String password, String name) {
		this(null, email, password, name);
	}

	public Member(Long id, String email, String password, String name) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	//== 비지니스 로직 ==//
	public void login(String password) {
		if (!password.equals(this.password)) {
			throw new IllegalArgumentException("Bad credential");
		}
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("email", email)
			.append("password", password)
			.append("name", name)
			.toString();
	}
}
