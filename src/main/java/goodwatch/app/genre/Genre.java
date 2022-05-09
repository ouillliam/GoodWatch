package goodwatch.app.genre;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import goodwatch.app.movie.Movie;

import javax.persistence.GenerationType;

@Entity
@Table(name = "genre")
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genreID;
   
    private String label;
    
    @ManyToMany
    @JoinTable(name = "genremovie",
    joinColumns = @JoinColumn(name = "genreID"),
    inverseJoinColumns = @JoinColumn(name = "movieID")
    )
    private List<Movie> movies;
    
    public Genre(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public Genre() {
        super();
    }

    // public Genre(String label) {
    //     this.label = label;
    // }

    public List<Movie> getMovies() {
        return movies;
    }


    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public long getGenreID() {
        return genreID;
    }

    public void setGenreID(long genreID) {
        this.genreID = genreID;
    }

}
