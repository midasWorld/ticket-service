package com.midas.ticket.user.web;

import static com.midas.ticket.common.ApiResponse.OK;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.midas.ticket.user.dto.JoinRequest;
import com.midas.ticket.user.dto.LoginRequest;
import com.midas.ticket.user.dto.MemberResponse;
import com.midas.ticket.user.service.MemberService;

@WebMvcTest(MemberRestController.class)
class MemberRestControllerTest {
	private static final String API_URL = "/api/v1/members";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MemberService memberService;

	private static final Long ID = 1L;
	private static final String EMAIL = "test77@gmail.com";
	private static final String PASSWORD = "1234";
	private static final String NAME = "test77";

	@Test
	@DisplayName("사용자가 회원가입 한다.")
	void test_join() throws Exception {
		JoinRequest joinRequest = new JoinRequest(EMAIL, PASSWORD, NAME);
		MemberResponse memberResponse = new MemberResponse(ID, NAME);

		String request = objectMapper.writeValueAsString(joinRequest);
		String response = objectMapper.writeValueAsString(OK(memberResponse));

		given(memberService.join(anyString(), anyString(), anyString())).willReturn(memberResponse);

		// when
		ResultActions perform = mockMvc.perform(post(API_URL)
			.content(request)
			.contentType(APPLICATION_JSON)
		);

		// then
		verify(memberService).join(anyString(), anyString(), anyString());

		perform
			.andExpect(status().isCreated())
			.andExpect(content().string(response));
	}

	@Test
	@DisplayName("사용자가 로그인 한다.")
	void test_login() throws Exception {
		LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);
		MemberResponse memberResponse = new MemberResponse(ID, NAME);

		String request = objectMapper.writeValueAsString(loginRequest);
		String response = objectMapper.writeValueAsString(OK(memberResponse));

		given(memberService.login(anyString(), anyString())).willReturn(memberResponse);

		// when
		ResultActions perform = mockMvc.perform(post(API_URL + "/login")
			.content(request)
			.contentType(APPLICATION_JSON)
		);

		// then
		verify(memberService).login(anyString(), anyString());

		perform
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}
}