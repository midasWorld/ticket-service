package com.midas.ticket.user.web;

import static com.midas.ticket.common.ApiResponse.OK;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.midas.ticket.common.ApiResponse;
import com.midas.ticket.user.dto.JoinRequest;
import com.midas.ticket.user.dto.MemberResponse;
import com.midas.ticket.user.service.MemberService;

@RestController
@RequestMapping("/api/v1/members")
public class MemberRestController {

	private final MemberService memberService;

	public MemberRestController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<MemberResponse> join(@RequestBody JoinRequest request) {
		MemberResponse member = memberService.join(request.getPrincipal(), request.getCredentials(), request.getName());

		return OK(member);
	}
}
