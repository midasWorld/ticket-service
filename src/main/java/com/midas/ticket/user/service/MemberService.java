package com.midas.ticket.user.service;

import org.springframework.stereotype.Service;

import com.midas.ticket.user.domain.Member;
import com.midas.ticket.user.dto.MemberResponse;
import com.midas.ticket.user.repository.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberResponse join(String principal, String credentials, String name) {
		Member member = new Member(principal, credentials, name);
		Member savedMember = memberRepository.save(member);

		return new MemberResponse(savedMember.getId(), name);
	}
}
