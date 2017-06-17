package com.rp.order.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.order.model.Member;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	private final String NAMESPACE = "com.easyshop.order.repository.MemberRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlTemplate;

	@Override
	public List<Member> selectMembers(Member member) {
		return sqlTemplate.selectList(NAMESPACE + "selectMembers", member);
	}

}
