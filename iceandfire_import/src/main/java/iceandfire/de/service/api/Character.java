package iceandfire.de.service.api;

import java.util.List;

public class Character {

	private String url;
	private String name;
	private String gender;
	private String culture;
	private String born;
	private List<String> titles;
	private List<String> aliases;
	private List<String> playedBy;
	
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCulture() {
		return culture;
	}
	public void setCulture(String culture) {
		this.culture = culture;
	}
	public String getBorn() {
		return born;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public List<String> getAliases() {
		return aliases;
	}
	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
	public List<String> getPlayedBy() {
		return playedBy;
	}
	public void setPlayedBy(List<String> playedBy) {
		this.playedBy = playedBy;
	}
	

	
}
