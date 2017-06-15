package com.rp.order.repository;

import java.util.List;

import com.rp.order.model.Member;

public interface MemberRepository {
	public List<Member> selectMembers(Member member);

}
