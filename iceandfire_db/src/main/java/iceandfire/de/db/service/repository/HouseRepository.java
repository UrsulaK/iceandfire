package iceandfire.de.db.service.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import iceandfire.de.db.service.model.House;

public interface HouseRepository extends PagingAndSortingRepository<House, String>{

	Page<House> findByNameLike(@Param("name") String name, Pageable pageable);
	Page<House> findByRegionLike(@Param("region") String region, Pageable pageable);
	Page<House> findByNameLikeAndRegionLike(@Param("name") String name, @Param("region") String region, Pageable pageable );

}
