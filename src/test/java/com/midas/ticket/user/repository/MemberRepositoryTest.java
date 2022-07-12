package com.midas.ticket.user.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.midas.ticket.user.domain.Member;

@DataJpaTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	private final Member newMember = new Member("test99@gmail.com", "1234", "test99");

	@Test
	@DisplayName("회원이 생성된다.")
	void test_create() {
		Member savedMember = memberRepository.save(newMember);

		assertThat(savedMember.getId(), notNullValue());
	}

	@Test
	@DisplayName("회원 이메일로 조회된다.")
	void test_findByEmail() {
		// given
		Member savedMember = memberRepository.save(newMember);

		// when
		Optional<Member> foundMember = memberRepository.findByEmail(savedMember.getEmail());

		// then
		assertThat(foundMember.isEmpty(), is(false));
		assertThat(foundMember.get(), samePropertyValuesAs(savedMember));
	}

	@Test
	@DisplayName("회원 정보가 삭제된다.")
	void test_delete() {
		Member savedMember = memberRepository.save(newMember);

		memberRepository.delete(savedMember);

		Optional<Member> deletedMember = memberRepository.findById(savedMember.getId());
		assertThat(deletedMember.isEmpty(), is(true));
	}

	@Test
	@DisplayName("중복된 이메일로 회원 생성 실패한다.")
	void test_fail_duplicated_email() {
		final Member savedMember = memberRepository.save(newMember);

		final String duplicatedEmail = savedMember.getEmail();
		final Member duplicatedMember = Member.builder()
			.email(duplicatedEmail)
			.password("1234")
			.name("test-fail")
			.build();


		assertThrows(DataIntegrityViolationException.class, () -> {
			memberRepository.save(duplicatedMember);
		});
	}

	@Nested
	@DisplayName("유효성 검사 실패 By")
	class ValidationFailedTest {
		private final String RIGHT_EMAIL = "test97@gmail.com";
		private final String RIGHT_PASSWORD = "1234";
		private final String RIGHT_NAME = "test97";

		private final String WRONG_EMAIL = "wrong!gmail.com";
		private final String WRONG_SIZE_PASSWORD = "123";
		private final String WRONG_SIZE_NAME = "n";
		private final String WRONG_OVERSIZE_NAME = "n".repeat(51);
		private final String EMPTY_STRING = "";

		@Test
		@DisplayName("빈 이메일 값")
		void test_fail_validateion_emptyEmail() {
			Member member = Member.builder()
				.email(EMPTY_STRING)
				.password(RIGHT_PASSWORD)
				.name(RIGHT_NAME)
				.build();

			assertThrows(ConstraintViolationException.class, () -> {
				memberRepository.save(member);
			});
		}

		@Test
		@DisplayName("빈 비밀번호 값")
		void test_fail_validateion_emptyPassword() {
			Member member = Member.builder()
				.email(RIGHT_EMAIL)
				.password(EMPTY_STRING)
				.name(RIGHT_NAME)
				.build();

			assertThrows(ConstraintViolationException.class, () -> {
				memberRepository.save(member);
			});
		}

		@Test
		@DisplayName("빈 이름 값")
		void test_fail_validateion_emptyName() {
			Member member = Member.builder()
				.email(RIGHT_EMAIL)
				.password(RIGHT_PASSWORD)
				.name(EMPTY_STRING)
				.build();

			assertThrows(ConstraintViolationException.class, () -> {
				memberRepository.save(member);
			});
		}

		@Test
		@DisplayName("유효하지 않은 이메일 형식")
		void test_fail_validateion_email() {
			Member member = Member.builder()
				.email(WRONG_EMAIL)
				.password(RIGHT_PASSWORD)
				.name(RIGHT_NAME)
				.build();

			assertThrows(ConstraintViolationException.class, () -> {
				memberRepository.save(member);
			});
		}

		@Test
		@DisplayName("최소 글자 미만의 비밀번호")
		void test_fail_validation_password_minSize() {
			Member member = Member.builder()
				.email(RIGHT_EMAIL)
				.password(WRONG_SIZE_PASSWORD)
				.name(RIGHT_NAME)
				.build();

			assertThrows(ConstraintViolationException.class, () -> {
				memberRepository.save(member);
			});
		}

		@Test
		@DisplayName("최소 글자 미만의 이름")
		void test_fail_validation_name_minSize() {
			Member member = Member.builder()
				.email(RIGHT_EMAIL)
				.password(RIGHT_PASSWORD)
				.name(WRONG_SIZE_NAME)
				.build();

			assertThrows(ConstraintViolationException.class, () -> {
				memberRepository.save(member);
			});
		}

		@Test
		@DisplayName("허용 글자 초과의 이름")
		void test_fail_validation_name_oversize() {
			Member member = Member.builder()
				.email(RIGHT_EMAIL)
				.password(RIGHT_PASSWORD)
				.name(WRONG_OVERSIZE_NAME)
				.build();

			assertThrows(ConstraintViolationException.class, () -> {
				memberRepository.save(member);
			});
		}
	}




}