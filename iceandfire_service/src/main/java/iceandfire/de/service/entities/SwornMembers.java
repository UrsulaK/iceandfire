package iceandfire.de.service.entities;

import java.util.List;

import iceandfire.de.service.model.SwornMember;

public class SwornMembers {

	private List<SwornMember> swornMemberList;
	private Page page = new Page();
	public List<SwornMember> getSwornMemberList() {
		return swornMemberList;
	}
	public void setSwornMemberList(List<SwornMember> swornMemberList) {
		this.swornMemberList = swornMemberList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
