package com.rp.member.web;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rp.member.model.Member;
import com.rp.member.service.MemberService;

@RestController
public class MemberController {
	private Logger trackingLogger = LoggerFactory.getLogger("log-tracker");
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(path = "/member/{id}", method = RequestMethod.GET)
	public Member member(@PathVariable String id) {
		return memberService.getMemberById(id);
	}

	@RequestMapping(path = "/member/random", method = RequestMethod.GET)
	public Member random() {
		List<Member> members = memberService.getMembers(new Member());

		if (CollectionUtils.isNotEmpty(members)) {
			int randomIdx = RandomUtils.nextInt(0, members.size() - 1);
			Member member = members.get(randomIdx);
			trackingLogger.info("Selected member : {}", member.getName());
			return member;
		}
		return null;
	}
}
