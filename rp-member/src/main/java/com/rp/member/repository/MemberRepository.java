package com.rp.member.repository;

import java.util.List;

import com.rp.member.model.Member;

public interface MemberRepository {
	public List<Member> selectMembers(Member member);
	
	public void addMember(Member member);

}
