package goodwatch.app.movie;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import goodwatch.app.genre.GenreService;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired 
    GenreService genreService;

    @GetMapping("/test")
    @ResponseBody
    public ArrayList<Movie> test(){
        return movieService.findAllMovie();
    }

    
    @GetMapping("/movies")
    public String getAllMovies(Model model){
        model.addAttribute("movies",movieService.findAllMovie()); 
        model.addAttribute("genres", genreService.findAllGenre());
        return "allMoviesView";
    }

    @GetMapping("/movie/{id}")
    public String getAllMovies(Model model, @PathVariable("id") long id){
        model.addAttribute("movie",movieService.getMovieByID(id).get()); 
        return "singleMovieView";
    }
    
}
