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
import iceandfire.de.service.entities.Houses;
import iceandfire.de.service.entities.SwornMembers;
import iceandfire.de.service.implementation.IceAndFireSearch;


@RestController
@RequestMapping("/service")
public class IceAndFireServiceController {

	@Autowired
	IceAndFireSearch iceAndFireSearch;
	
	
	@CrossOrigin
	@RequestMapping(value="/houses/search/region/{region}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Houses> getHousesByRegion(@PathVariable(name = "region", required = true) final String region,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		
		return Optional.ofNullable(iceAndFireSearch.findHousesByRegion(region, page, pageSize))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
		
	
	@CrossOrigin
	@RequestMapping(value="/houses/search/{name}/{region}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Houses> getHousesByNameAndByRegion(
			@PathVariable(name = "name", required = true) final String name,
			@PathVariable(name = "region", required = true) final String region,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		
		return Optional.ofNullable(iceAndFireSearch.findHousesByNameAndRegion(name, region, page, pageSize))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
		
	}

	@CrossOrigin
	@RequestMapping(value="/swornMembers/{name}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SwornMembers> getSwornMembersByName(
			@PathVariable(name = "name", required = true) final String name,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		
		return Optional.ofNullable(iceAndFireSearch.findSwornMembersByName(name, page, pageSize))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
		
	}
		
	@CrossOrigin
	@RequestMapping(value="/houses/search/name/{name}/{page}/{pageSize}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Houses> getHousesByName(
			@PathVariable(name = "name", required = true) final String name,
			@PathVariable(name = "page", required = true) final String page,
			@PathVariable(name = "pageSize", required = true) final String pageSize){
		    
		return Optional.ofNullable(iceAndFireSearch.findHousesByName(name, page, pageSize))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	
}
