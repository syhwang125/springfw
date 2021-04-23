package com.spring.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.aggregate.club.TravelClub;
import com.spring.service.ClubService;
import com.spring.service.sdo.TravelClubCdo;
import com.spring.shared.NameValueList;
import com.spring.store.ClubStore;
import com.spring.util.exception.NoSuchClubException;

@Service("clubService")
public class ClubServiceLogic implements ClubService {

	private ClubStore clubStore;
	public ClubServiceLogic(ClubStore clubStore) {
		this.clubStore = clubStore;
	}
	
	@Override
	public String registerClub(TravelClubCdo club) {
		TravelClub newClub = new TravelClub(club.getName(), club.getIntro());
		newClub.checkValidation();
		return clubStore.create(newClub);
	}
	
	@Override
	public TravelClub findClubById(String id) {		
		return clubStore.retrieve(id);
	}
	
	@Override
	public List<TravelClub> findClubsByName(String name) {
		return clubStore.retrieveByName(name);
	}
	
	@Override
	public void modify(String clubId, NameValueList nameValues) {
		TravelClub foundedClub = clubStore.retrieve(clubId);
		if(foundedClub == null) {
			throw new NoSuchClubException("No such club with id : " + clubId);
		}
		foundedClub.modifyValues(nameValues);
		clubStore.update(foundedClub);
	}
	
	@Override
	public void remove(String clubId) {
		if(!clubStore.exists(clubId) ) {
			throw new NoSuchClubException("No such club with id : " + clubId);
		}
		clubStore.delete(clubId);
	}

    public String registerClub( com.spring.service.TravelClubCdo club ) {
        // TODO Auto-generated method stub
        return null;
    }

    public com.spring.service.TravelClub findClubById( String id ) {
        // TODO Auto-generated method stub
        return null;
    }

    public void modify( String clubId, com.spring.service.NameValueList nameValues ) {
        // TODO Auto-generated method stub
        
    }
	

}
