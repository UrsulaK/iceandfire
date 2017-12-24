package iceandfire.de.db.service.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import iceandfire.de.db.service.model.House;

public interface HouseRepository extends PagingAndSortingRepository<House, String>{

	
}
