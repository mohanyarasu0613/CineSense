package com.spring.CineSense.DTO;

public class MovieDTO {

    private String imdbId;
    private String title;
    private String year;
    private String poster;
    
	public MovieDTO() {
		super();
	}
	
	public MovieDTO(String imdbId, String title, String year, String poster) {
		super();
		this.imdbId = imdbId;
		this.title = title;
		this.year = year;
		this.poster = poster;
	}
	
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
    
}
