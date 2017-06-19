package com.rp.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rp.member.model.Member;
import com.rp.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepository;

	@Cacheable("memberInfos")
	@Override
	public List<Member> getMembers(Member member) {
		return memberRepository.selectMembers(member);
	}

	@Override
	public Member getMemberById(String mbrId) {
		Member sqlParam = new Member();
		sqlParam.setMbrId(mbrId);
		List<Member> members = memberRepository.selectMembers(sqlParam);
		
		if(members !=null && members.size() == 1) {
			return members.get(0);
		}
		
		return null;
	}

}
