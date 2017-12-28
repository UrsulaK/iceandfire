package iceandfire.de.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;
import iceandfire.de.service.converter.PageableResourcesConverter;
import iceandfire.de.service.entities.Houses;
import iceandfire.de.service.entities.SwornMembers;
import iceandfire.de.service.feign.IceAndFireHouseDBClient;
import iceandfire.de.service.feign.IceAndFireSwornMemberDBClient;
import iceandfire.de.service.model.House;
import iceandfire.de.service.model.SwornMember;

@Service
public class IceAndFireSearch {

	
	@Autowired
	private IceAndFireHouseDBClient houseDBClient;
	@Autowired
	private IceAndFireSwornMemberDBClient swornMemberDBClient;
	@Autowired
	private PageableResourcesConverter resourcesConverter;
	
	
	
	public Houses findHousesByName(String name, String page, String size){
		PagedResources<House> housesResource = houseDBClient.findByNameLike(name, page, size);
		Houses houses = resourcesConverter.convertHouseResources(housesResource);
		return houses;
	}
	
	public Houses findHousesByRegion(String region, String page, String size){
		PagedResources<House> housesResource = houseDBClient.findByRegionLike(region, page, size);
		Houses houses = resourcesConverter.convertHouseResources(housesResource);
		return houses;
	}
	
	public Houses findHousesByNameAndRegion(String name, String region, String page, String size){
		PagedResources<House> housesResource = houseDBClient.findByNameLikeAndRegionLike(name, region, page, size);
		Houses houses = resourcesConverter.convertHouseResources(housesResource);
		return houses;
	}
	
	public SwornMembers findSwornMembersByName(String name, String page, String size){
		PagedResources<SwornMember> swornMembersResource = swornMemberDBClient.findByNameLike(name, page, size);
		SwornMembers swornMembers = resourcesConverter.convertSwornMemberResources(swornMembersResource);
		return swornMembers;
	}
}
