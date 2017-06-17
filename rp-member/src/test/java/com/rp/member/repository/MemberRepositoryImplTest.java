
package com.rp.member.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.rp.member.model.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryImplTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberRepository repository;

	private List<Member> defaultMemberData = new ArrayList<>();

	private final String[] DEFAULT_ADDRS = { "서울시 서대문구 홍은동", "서울시 동대문구 장안동", "경기도 성남시 분당구 이매동", "서울시 은평구 갈현동",
			"서울시 종로구 청운동", "서울시 마포구 광흥창동", "대전시 서구 도마동", "대전시 중구 은행동", "서울시 서초구 양재동", "서울시 서초구 반포1동", "서울시 강남구 삼성동" };

	//http://scloudy.com/random/name
	private final String DEFAULT_NAMES_STR = "임욱,길달수,양지선,방현숙,우현주,류영롱,손정숙,공병인,류희덕,권동춘,권준오,공신호,심승연,이정혁,나설아,길창원,강범석,우솔희,방경혜,양백기,백준석,구정옥,강경선,표중수,진윤태,손은아,정정미,손경선,채의정,노승리,주문별,허윤태,윤민규,고주용,강형진,나혜림,안민균,류창우,손성욱,심현지,주지만,허현,이주안,왕여옥,윤린,이하은,차종덕,류하은,차의철,우주용,대태식,왕희덕,진준학,장성욱,유정복,장아람,지현철,노수철,남정재,장세준,서경화,송혜진,표한별,표호준,소상재,박휘재,진주만,진재민,송종수,신인철,제갈정종,심은비,주성원,최대종,허윤성,표승권,오찬주,배기덕,노경석,채보민,문세현,류민서,박현,심미애,심영아,최준호,방정호,유희경,방상훈,남궁숙희,윤시라,대보희,한수아,임이지,표인희,채호섭,백재범,한호석,우효신,진성미";
	
	private final String[] DEFAULT_NAMES = DEFAULT_NAMES_STR.split(",");
	@Before
	public void setDefaultMember() {		

		for (int i = 0; i < 100; i++) {
			Member member = new Member();
			member.setMbrId(RandomStringUtils.random(RandomUtils.nextInt(8, 15), true, true));
			member.setSex(i % 2 == 0 ? "F" : "M");
			member.setAddr(getRandomAddr());
			member.setName(getRandomName(i));
			member.setBirth(getRandomBirth());
			defaultMemberData.add(member);
		}
	}

	@Test
	public void testGetMember() {
		Member member = new Member();
		List<Member> members = repository.selectMembers(member);
		for(Member one : members) {
			logger.info(one.toString());
		}
	}

	@Test
	public void testAddDefaultMember() {
		for(Member member : defaultMemberData) {
			logger.info(member.toString());
			repository.insertyMember(member);
		}
	}

	private String getRandomName(int i) {
		return DEFAULT_NAMES[i];
	}

	private String getRandomBirth() {
		String yyyy = Integer.toString(RandomUtils.nextInt(1970, 2000));
		String mm = Integer.toString(RandomUtils.nextInt(1, 12));
		if(mm.length() == 1) {
			mm += "0";
		}
		String dd = Integer.toString(RandomUtils.nextInt(1, 30));
		if(dd.length() == 1) {
			dd += "0";
		}
		return yyyy + mm + dd;
	}
	
	private String getRandomAddr() {
		String addr = DEFAULT_ADDRS[RandomUtils.nextInt(0, DEFAULT_ADDRS.length - 1)] + " "
				+ RandomUtils.nextInt(100, 999) 
				+ "-" 
				+ RandomUtils.nextInt(100, 999);
		return addr;
	}

}
