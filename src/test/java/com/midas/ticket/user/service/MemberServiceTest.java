package com.midas.ticket.user.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.midas.ticket.user.domain.Member;
import com.midas.ticket.user.dto.MemberResponse;
import com.midas.ticket.user.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	MemberRepository memberRepository;

	@InjectMocks
	MemberService memberService;

	@Test
	@DisplayName("사용자가 회원가입 한다.")
	void test_join() {
		final Long id = 1L;
		final String email = "test77@naver.com";
		final String password = "1234";
		final String name = "test77";

		final Member member = new Member(id, email, password, name);
		final MemberResponse response = new MemberResponse(id, name);

		given(memberRepository.save(any(Member.class))).willReturn(member);

		// when
		MemberResponse joinedMember = memberService.join(email, password, name);

		// then
		verify(memberRepository).save(any(Member.class));

		assertThat(joinedMember, samePropertyValuesAs(response));
	}
}