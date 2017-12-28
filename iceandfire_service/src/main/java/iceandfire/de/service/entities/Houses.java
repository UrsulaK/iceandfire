package iceandfire.de.service.entities;

import java.util.List;

import iceandfire.de.service.model.House;

public class Houses {

	private List<House> houseList;
	
	private Page page = new Page();

	public List<House> getHouseList() {
		return houseList;
	}

	public void setHouseList(List<House> houseList) {
		this.houseList = houseList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
