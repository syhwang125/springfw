package com.spring;

import java.util.Arrays;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aggregate.club.CommunityMember;
import com.spring.aggregate.club.TravelClub;
import com.spring.service.ClubService;
import com.spring.service.MemberService;
import com.spring.service.sdo.MemberCdo;
import com.spring.service.sdo.TravelClubCdo;
import com.spring.shared.NameValueList;

public class TravelClubApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 등록된 빈 정보 확인하기 
		String[] beanNames = context.getBeanDefinitionNames();
		System.out.println(Arrays.toString(beanNames));   // [clubStore, clubService]
		
		System.out.println();
		System.out.println(" -----  1. travelclub  register  ------- ");
		
		TravelClubCdo clubCdo = new TravelClubCdo("TravelClub", "Test TravelClub");
		ClubService clubService = context.getBean("clubService", ClubService.class);
		
		String clubId = clubService.registerClub(clubCdo);
		System.out.println("Club id : " + clubId);
		
		TravelClub foundedClub = clubService.findClubById(clubId);
		
		System.out.println("Club name : " + foundedClub.getName());
		System.out.println("Club intro : " + foundedClub.getIntro());
		System.out.println("Club foundationTime : " + new Date(foundedClub.getFoundationTime()));  
		System.out.println("Club id : " + foundedClub.getId());
		
		System.out.println();
		System.out.println(" -----  2. travelclub modify  result  ------- ");
		
		NameValueList nameValues = new NameValueList();
		nameValues.addNameValue("name","healing-club");
		nameValues.addNameValue("intro","healing club intro");
		clubService.modify(foundedClub.getId(), nameValues);
		
		System.out.println("Club name : " + foundedClub.getName());
		System.out.println("Club intro : " + foundedClub.getIntro());
		System.out.println("Club foundationTime : " + new Date(foundedClub.getFoundationTime()));  
		System.out.println("Club id : " + foundedClub.getId());
		
		System.out.println();
		System.out.println(" -----  3. member register & find  ------- ");
		
		MemberService memberService = context.getBean("memberServiceLogic", MemberService.class);
		
		String memberId = memberService.registerMember( 
				new MemberCdo(
						"test@poscoict.com",
						"Hwang",
						"Team Member",
						"010-111-2222",
						"2010.10.10"));
		
		CommunityMember foundedMember = memberService.findMemberById(memberId);
		System.out.println(foundedMember.toString()); 
	}
}
