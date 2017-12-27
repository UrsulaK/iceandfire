package iceandfire.de.service.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import iceandfire.de.service.implementation.IceAndFireSearch;

@RestController
@RequestMapping("/iceandfire")
public class IceAndFireServiceController {

	@Autowired
	IceAndFireSearch iceAndFireSearch;
	
	@CrossOrigin
	@RequestMapping(value="/houses/search/name/{name}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getHousesByName(
			@PathVariable(name = "name", required = true) final String name,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		
		
		return Optional.ofNullable(iceAndFireSearch.searchHousesByName(name, page, pageSize))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	
	@CrossOrigin
	@RequestMapping(value="/houses/search/region/{region}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getHousesByRegion(
			@PathVariable(name = "region", required = true) final String region,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		
		
		return Optional.ofNullable(iceAndFireSearch.searchHousesByRegion(region, page, pageSize))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	@CrossOrigin
	@RequestMapping(value="/houses/search/{name}/{region}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getHousesByNameAndByRegion(
			@PathVariable(name = "name", required = true) final String name,
			@PathVariable(name = "region", required = true) final String region,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		
		
		return Optional.ofNullable(iceAndFireSearch.searchHousesByNameAndByRegion(name, region, page, pageSize))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	@CrossOrigin
	@RequestMapping(value="/swornMembers/{name}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getSwornMembersByName(
			@PathVariable(name = "name", required = true) final String name,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		
		
		return Optional.ofNullable(iceAndFireSearch.searchSwornMembersByName(name, page, pageSize))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	
	
}
