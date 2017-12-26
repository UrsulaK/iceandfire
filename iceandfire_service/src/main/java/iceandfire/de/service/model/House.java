package iceandfire.de.service.model;

import java.util.List;

public class House {

	private String id;
	
	private String url;
	private String name;
	private String region;
	private String coatOfArms;
	private String words;
	private List<String> titles;
	private String currentLord;
	private String founder;
	
	private List<String> swornMembers;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCoatOfArms() {
		return coatOfArms;
	}
	public void setCoatOfArms(String coatOfArms) {
		this.coatOfArms = coatOfArms;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public String getCurrentLord() {
		return currentLord;
	}
	public void setCurrentLord(String currentLord) {
		this.currentLord = currentLord;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public List<String> getSwornMembers() {
		return swornMembers;
	}
	public void setSwornMembers(List<String> swornMembers) {
		this.swornMembers = swornMembers;
	}
}
