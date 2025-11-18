package com.skillstorm.springaop.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movie {
    
    @Id
    private String id;

    private String title;
    private int rating;
    private Director director;
    
    public Movie() {

    }

    public Movie(String title, int rating, Director director) {
        this.title = title;
        this.rating = rating;
        this.director = director;
    }

    public Movie(String id, String title, int rating, Director director) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.director = director;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + rating;
        result = prime * result + ((director == null) ? 0 : director.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (id != other.id)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (rating != other.rating)
            return false;
        if (director == null) {
            if (other.director != null)
                return false;
        } else if (!director.equals(other.director))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", rating=" + rating + ", director=" + director + "]";
    }

    

}
