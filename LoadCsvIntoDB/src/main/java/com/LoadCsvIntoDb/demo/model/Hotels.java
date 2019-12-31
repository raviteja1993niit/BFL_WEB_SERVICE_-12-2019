package com.LoadCsvIntoDb.demo.model;

public class Hotels {
	
	private String id;
	private String name;
	private String description;
	private String city;
	private String rating;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Hotels(String id, String name, String description, String city, String rating) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.city = city;
		this.rating = rating;
	}
	public Hotels()
	{
		
	}
	
	@Override
	public String toString() {
		return "Hotels [id=" + id + ", name=" + name + ", description=" + description + ", city=" + city + ", rating="
				+ rating + "]";
	}
	
	

}
