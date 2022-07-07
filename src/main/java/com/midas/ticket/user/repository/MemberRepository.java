package com.midas.ticket.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midas.ticket.user.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
