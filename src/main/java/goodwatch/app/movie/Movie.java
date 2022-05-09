package goodwatch.app.movie;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import goodwatch.app.director.Director;
import goodwatch.app.genre.Genre;
import goodwatch.app.rating.Rating;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieID;
    private String title;
    @Column(name = "releaseyear")
    private int releaseYear;
    private String plot;
    private String poster;
    private int runtime;

    @ManyToOne
    @JoinColumn(name = "directorID")
    private Director director;

    @ManyToMany
    @JoinTable(name = "genremovie",
    joinColumns = @JoinColumn(name = "movieID"),
    inverseJoinColumns = @JoinColumn(name = "genreID"))
    private List<Genre> genres;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "movieid", referencedColumnName = "movieid")
    private List<Rating> ratings;
    
    public Movie(String title, int releaseYear, String plot, String poster, int runtime, Director director,
            ArrayList<Genre> genres) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.plot = plot;
        this.poster = poster;
        this.runtime = runtime;
        this.director = director;
        this.genres = genres;
    }

    // public Movie(String title, int releaseYear, String plot, String poster, int runtime, Director director) {
    //     this.title = title;
    //     this.releaseYear = releaseYear;
    //     this.plot = plot;
    //     this.poster = poster;
    //     this.runtime = runtime;
    //     this.director = director;
    // }

    public Movie(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public String getAvgRating(){
        double sum = 0;
        for (Rating rating : this.ratings) {
            sum += rating.getRating();
        }
        double avg = sum / this.ratings.size();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return df.format(avg);
    }

   
}
