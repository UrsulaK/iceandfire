package iceandfire.de.db.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import iceandfire.de.db.service.model.SwornMember;

public interface SwornMemberRepository extends PagingAndSortingRepository<SwornMember, String>{
	
//	Page<SwornMember> findFirstnameLikeOrLastnameLikeOrNameLike(String firstname, String lastname, String name, Pageable pageable);
//	@Query("{ 'candidate' : ?0 }")
//	default Page<SwornMember> findByFirstnameOrLastnameOrName(String candidate, Pageable pageable) {
//		return findFirstnameLikeOrLastnameLikeOrNameLike(candidate, candidate, candidate, pageable );
//	}
		
}
	
