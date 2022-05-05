package goodwatch.app.director;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import goodwatch.app.movie.Movie;

@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;
    
    @GetMapping("/directors")
    public ArrayList<Director> getAllDirectors(){
        return directorService.findAllDirector();
    }

    @GetMapping("/director/{id}/movies")
    public ArrayList<Movie> getDirectorMovieByID(@PathVariable("id") long id){
        Optional<Director> director = directorService.getDirectorById(id);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.addAll(director.get().getMovies());
        return movies;
    }
}
