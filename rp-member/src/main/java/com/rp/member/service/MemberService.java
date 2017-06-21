package com.rp.member.service;

import java.util.List;

import com.rp.member.model.Member;

public interface MemberService {
	List<Member> getMembers(Member member);
	
	Member getMemberById(String id);

}
