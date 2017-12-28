package iceandfire.de.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import iceandfire.de.service.model.House;

@FeignClient(name = "iceandfiredb", path = "/houses/search/")
public interface IceAndFireHouseDBClient {

	@RequestMapping(method = RequestMethod.GET, path = "findByNameLike")
	PagedResources<House> findByNameLike(@RequestParam("name") String name, @RequestParam("page")  String page, @RequestParam("size")  String size);
	
	@RequestMapping(method = RequestMethod.GET, path = "findByRegionLike")
	PagedResources<House> findByRegionLike(@RequestParam("region") String region, @RequestParam("page")  String page, @RequestParam("size")  String size);
	
	@RequestMapping(method = RequestMethod.GET, path = "findByNameLikeAndRegionLike")
	PagedResources<House> findByNameLikeAndRegionLike(@RequestParam("name") String name, @RequestParam("region") String region, @RequestParam("page")  String page, @RequestParam("size")  String size);
	
	
}
