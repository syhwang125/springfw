/**
 * 
 */
package com.spring.store.mapstore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.spring.aggregate.club.TravelClub;
import com.spring.store.ClubStore;

/**
 * @author syhwang
 *
 */
@Repository("clubStore")
public class ClubMapStore implements ClubStore {
	
	private Map<String, TravelClub> clubMap;

	public ClubMapStore() {
		this.clubMap = new LinkedHashMap<>();
	}
	
	@Override
	public String create(TravelClub club) {
		this.clubMap.put(club.getId(), club);
		return club.getId();
	}

	@Override
	public TravelClub retrieve(String clubId) {
		return clubMap.get(clubId);
	}

	@Override
	public List<TravelClub> retrieveByName(String name) {
		/* @formatter:off */  
		return clubMap.values()
				      .stream()
				      .filter( club -> club.getName().equals(name))
				      .collect(Collectors.toList());
		/* @formatter:on */
	}

	@Override
	public void update(TravelClub club) {
		clubMap.put(club.getId(), club);
	}

	@Override
	public void delete(String clubId) {
		clubMap.remove(clubId);

	}

	@Override
	public boolean exists(String clubId) {
//		return clubMap.containsKey(clubId);
		return Optional.ofNullable(clubMap.get(clubId)).isPresent();
	}

}
