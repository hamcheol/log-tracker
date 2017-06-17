package com.rp.member.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.member.model.Member;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	private final String NAMESPACE = "com.rp.member.repository.MemberRepositoryImpl.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Member> selectMembers(Member member) {
		return sqlSession.selectList(NAMESPACE + "selectMembers", member);
	}

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub

	}

}
