package com.rp.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.rp.member.model.Member;

@Component
public class MemberCacheInitializer implements ApplicationListener<ApplicationReadyEvent> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private MemberService memberService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("====================> Call onApplicationEvent");
		List<Member> members = memberService.getMembers(new Member());
		Cache memberInfos = cacheManager.getCache("memberInfos");
		memberInfos.clear();
		for(Member member : members) {
			memberInfos.putIfAbsent(member.getId(), member);
		}
		logger.info("====================> Done onApplicationEvent");
	}
}
