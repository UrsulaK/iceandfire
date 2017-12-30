package iceandfire.de.service.rest;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import iceandfire.de.service.implementation.IceAndFireImportService;

@RestController
@RequestMapping("/iceandfire")
public class IceAndFireController {
	
	@Autowired
	private IceAndFireImportService iceAndFireService;
	
	
	

	@CrossOrigin
	@RequestMapping(value="/import", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> importIceAndFireData(){
		
		return Optional.ofNullable(iceAndFireService.importFireAndIceData())
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	
	
}
