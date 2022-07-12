package com.midas.ticket.user.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

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

	private static final Long ID = 1L;
	private static final String EMAIL = "test77@naver.com";
	private static final String PASSWORD = "1234";
	private static final String NAME = "test77";

	@Test
	@DisplayName("사용자가 회원가입 한다.")
	void test_join() {
		final Member member = new Member(ID, EMAIL, PASSWORD, NAME);
		final MemberResponse response = new MemberResponse(ID, NAME);

		given(memberRepository.save(any(Member.class))).willReturn(member);

		// when
		MemberResponse joinedMember = memberService.join(EMAIL, PASSWORD, NAME);

		// then
		verify(memberRepository).save(any(Member.class));

		assertThat(joinedMember, samePropertyValuesAs(response));
	}

	@Test
	@DisplayName("사용자가 로그인 한다.")
	void test_login() {
		final Member member = new Member(ID, EMAIL, PASSWORD, NAME);
		final MemberResponse response = new MemberResponse(ID, NAME);

		given(memberRepository.findByEmail(any(String.class))).willReturn(Optional.of(member));

		// when
		MemberResponse loginedMember = memberService.login(EMAIL, PASSWORD);

		// then
		verify(memberRepository).findByEmail(any(String.class));

		assertThat(loginedMember, samePropertyValuesAs(response));
	}

	@Test
	@DisplayName("존재하지 않는 이메일로 로그인 실패한다.")
	void test_fail_notExistsByEmail() {
		final String notExistsEmail = "wrong@gmail.com";

		when(memberRepository.findByEmail(notExistsEmail)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> {
			memberService.login(notExistsEmail, PASSWORD);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("일치하지 않는 비밀번호로 로그인 실패한다.")
	void test_fail_wrongPassword() {
		final String wrongPassword = "wrong1234";
		final Member member = new Member(ID, EMAIL, PASSWORD, NAME);

		given(memberRepository.findByEmail(EMAIL)).willReturn(Optional.of(member));

		assertThatThrownBy(() -> {
			memberService.login(EMAIL, wrongPassword);
		}).isInstanceOf(IllegalArgumentException.class);
	}
}