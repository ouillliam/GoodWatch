package goodwatch.app.director;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.sym.Name;

import org.hibernate.annotations.ManyToAny;

import goodwatch.app.movie.Movie;

@Entity
@Table(name = "director")
public class Director {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long directorID;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "director")
    //@JoinColumn(name = "directorID")
    private List<Movie> movies;

    public Director(String name) {
        this.name = name;
    }

    public Director(){
        super();
    }

    public long getDirectorID() {
        return directorID;
    }

    public void setDirectorID(long directorID) {
        this.directorID = directorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
