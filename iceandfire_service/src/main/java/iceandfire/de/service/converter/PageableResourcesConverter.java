package iceandfire.de.service.converter;

import java.util.ArrayList;

import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Component;

import iceandfire.de.service.entities.Houses;
import iceandfire.de.service.entities.SwornMembers;
import iceandfire.de.service.model.House;
import iceandfire.de.service.model.SwornMember;
@Component
public class PageableResourcesConverter {

	public Houses convertHouseResources(PagedResources<House> housesResource){
		Houses houses = new Houses();
		houses.setHouseList(new ArrayList<House>(housesResource.getContent()));
		houses.getPage().setNumber(housesResource.getMetadata().getNumber());
		houses.getPage().setSize(housesResource.getMetadata().getSize());
		houses.getPage().setTotalPages(housesResource.getMetadata().getTotalPages());
		return houses;
	}
	
	public SwornMembers convertSwornMemberResources(PagedResources<SwornMember> swornMembersResource){
		SwornMembers swornMembers = new SwornMembers();
		swornMembers.setSwornMemberList(new ArrayList<SwornMember>(swornMembersResource.getContent()));
		swornMembers.getPage().setNumber(swornMembersResource.getMetadata().getNumber());
		swornMembers.getPage().setSize(swornMembersResource.getMetadata().getSize());
		swornMembers.getPage().setTotalPages(swornMembersResource.getMetadata().getTotalPages());
		return swornMembers;
	}
}
