package iceandfire.de.service.converter;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import iceandfire.de.service.api.ApiCharacter;
import iceandfire.de.service.api.ApiHouse;
import iceandfire.de.service.db.House;
import iceandfire.de.service.db.SwornMember;

@Component
public class IceAndFireConverter {

	public House convertApiHouseToHouse(ApiHouse apiHouse){
		House house = new House();
		
		house.setId(getIdFromUrl(apiHouse.getUrl()));
		house.setCoatOfArms(apiHouse.getCoatOfArms());
		house.setCurrentLord(apiHouse.getCurrentLord());
		house.setFounder(apiHouse.getFounder());
		house.setName(apiHouse.getName());
		house.setRegion(apiHouse.getRegion());
		house.setSwornMembers(new ArrayList<>());
		house.setTitles(apiHouse.getTitles());
		house.setUrl(apiHouse.getUrl());
		house.setWords(apiHouse.getWords());
		return house;
	}
	
	public SwornMember convertApiCharacterToSwornMember(ApiCharacter character){
		SwornMember swornMember = new SwornMember();
		swornMember.setId(getIdFromUrl(character.getUrl()));
		swornMember.setAliases(character.getAliases());
		swornMember.setBorn(character.getBorn());
		swornMember.setCulture(character.getCulture());
		swornMember.setName(character.getName());
		swornMember.setFirstname(getFirstnameFromName(character.getName()));
		swornMember.setLastname(getLastnameFromName(character.getName()));
		swornMember.setPlayedBy(character.getPlayedBy());
		swornMember.setTitles(character.getTitles());
		swornMember.setUrl(character.getUrl());
		return swornMember;
	}
	private String getIdFromUrl(String url){
		String[] parts = url.split("/");
		return parts[parts.length - 1];
	}
	
	private String getFirstnameFromName(String name){
		String[] parts = name.split(" ");
		return parts[0];
	}
	
	private String getLastnameFromName(String name){
		String[] parts = name.split(" ");
		
		return parts[parts.length - 1];
	}
}
