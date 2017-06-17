
package com.rp.member.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rp.member.model.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryImplTest {
	@Autowired
	private MemberRepository repository;
	
	@Test
	public void testGetMember() {
		Member member = new Member();
		List<Member> members = repository.selectMembers(member);
	}
	
	@Test
	public void testAddDefaultMember() {
		
	}

}
