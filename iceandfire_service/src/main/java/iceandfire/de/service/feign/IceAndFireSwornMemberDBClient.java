package iceandfire.de.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import iceandfire.de.service.model.SwornMember;

@FeignClient(name = "iceandfiredb", path = "/swornMembers/search/")
public interface IceAndFireSwornMemberDBClient {

	@RequestMapping(method = RequestMethod.GET, path = "findByNameLike")
	PagedResources<SwornMember> findByNameLike(@RequestParam("name") String name, @RequestParam("page")  String page, @RequestParam("size")  String size);
	
}
