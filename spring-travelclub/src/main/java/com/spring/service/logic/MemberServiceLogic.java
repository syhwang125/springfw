package com.spring.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aggregate.club.CommunityMember;
import com.spring.service.MemberService;
import com.spring.service.sdo.MemberCdo;
import com.spring.shared.NameValueList;
import com.spring.store.MemberStore;
import com.spring.util.exception.MemberDuplicationException;
import com.spring.util.exception.NoSuchMemberException;

@Service
public class MemberServiceLogic implements MemberService {

	@Autowired 
	private MemberStore memberStore;
	
//	public MemberServiceLogic(MemberStore memberStore) {
//		this.memberStore = memberStore;
//	}
	
	@Override
	public String registerMember(MemberCdo member) {
		String email = member.getEmail();
		CommunityMember foundedMember = memberStore.retrieveByEmail(email);
		
		if(foundedMember != null) {
			throw new MemberDuplicationException("Member already exists with email : " + email) ;
		}
		
		foundedMember = new CommunityMember ( 
					member.getEmail(), member.getName(), member.getPhoneNumber() );
		foundedMember.setNickName(member.getNickName());
		foundedMember.setBirthDay(member.getBirthDay());
		
		foundedMember.checkValidation();
		memberStore.create(foundedMember);
				
		return foundedMember.getId();
	}

	@Override
	public CommunityMember findMemberById(String memberId) {
		return memberStore.retrieve(memberId);
	}

	@Override
	public CommunityMember findMemberByEmail(String memberEmail) {
		return memberStore.retrieveByEmail(memberEmail);
	}

	@Override
	public List<CommunityMember> findMembersByName(String name) {
		return memberStore.retrieveByName(name);
	}

	@Override
	public void modifyMember(String memberId, NameValueList nameValueList) {
		CommunityMember targetMember = memberStore.retrieve(memberId);
		if(targetMember == null) {
			throw new NoSuchMemberException("No such member with id : " + memberId);
		}
		targetMember.modifyValues(nameValueList);
		memberStore.update(targetMember);
	}

	@Override
	public void removeMember(String memberId) {
		if(!memberStore.exists(memberId)) {
			throw new NoSuchMemberException("No such member with id : " + memberId);
		}
		memberStore.delete(memberId);
	}

}
