package iceandfire.de.db.service.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class SwornMember {

	@Id
	private String id;
	
	private String name;
	private String firstname;
	private String lastname;
	private String url;
	private String gender;
	private String culture;
	private String born;
	private List<String> titles;
	private List<String> aliases;
	private List<String> playedBy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
