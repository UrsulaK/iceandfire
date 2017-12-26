package iceandfire.de.db.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import iceandfire.de.db.service.model.SwornMember;

public interface SwornMemberRepository extends PagingAndSortingRepository<SwornMember, String>{
	
	
	Page<SwornMember> findByFirstnameLikeOrLastnameLikeOrNameLike(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("name") String name, Pageable pageable);
	
	Page<SwornMember> findByNameLike(@Param("name") String name, Pageable pageable);
	
	
//	default Page<SwornMember> findByFirstnameLikeOrLastnameLikeOrNameLike(@Param("name")String name, Pageable pageable) {
//		return findByFirstnameLikeOrLastnameLikeOrNameLike(name, name, name, pageable );
//	}
		
}
	
