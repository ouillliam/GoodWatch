package goodwatch.app.movie;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import goodwatch.app.director.Director;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieID;
    private String title;
    private int releaseYear;
    private String plot;
    private String poster;
    private int runtime;

    private long directorID;
    
    public Movie(String title, int releaseYear, String plot, String poster, int runtime, long directorID) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.plot = plot;
        this.poster = poster;
        this.runtime = runtime;
        this.directorID = directorID;
    }

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

    public long getdirectorID() {
        return directorID;
    }

    public void setdirectorID(long directorID) {
        this.directorID = directorID;
    }

   
}
