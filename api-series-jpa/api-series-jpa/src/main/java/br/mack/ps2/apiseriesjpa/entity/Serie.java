package br.mack.ps2.apiseriesjpa.entity;

import javax.persistence.*;

@Entity
@Table (name = "series")
public class Serie {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    //@Column(nullable = false)
    //@Column(unique = true, nullable = false)
    private String url, title, director, mainCast, genre, synopsis, streaming, awards;
    private int year, seasons, rating;

    //construtor padrão:
    public Serie() {}

    //outro construtor:
    public Serie(String url, String title, String director, String mainCast, String genre, String synopsis, String streaming, String awards, int year, int seasons, int rating) {
        this.url = url;
        this.title = title;
        this.director = director;
        this.mainCast = mainCast;
        this.genre = genre;
        this.synopsis = synopsis;
        this.streaming = streaming;
        this.awards = awards;
        this.year = year;
        this.seasons = seasons;
        this.rating = rating;
    }

    //Getters and Setters:

    /**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainCast() {
        return mainCast;
    }

    public void setMainCast(String mainCast) {
        this.mainCast = mainCast;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getStreaming() {
        return streaming;
    }

    public void setStreaming(String streaming) {
        this.streaming = streaming;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}