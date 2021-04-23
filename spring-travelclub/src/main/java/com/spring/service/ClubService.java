package com.spring.service;

import com.spring.aggregate.club.TravelClub;
import com.spring.service.sdo.TravelClubCdo;
import com.spring.shared.NameValueList;

import java.util.List;

public interface ClubService {
	//
	String registerClub(TravelClubCdo club);
	TravelClub findClubById(String id);
	List<TravelClub> findClubsByName(String name);
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}
