package goodwatch.app.director;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import goodwatch.app.genre.GenreService;
import goodwatch.app.movie.Movie;

@Controller
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @Autowired
    GenreService genreService;
    
    @GetMapping("/directors")
    public String getAllDirectors(Model model){
        model.addAttribute("directors", directorService.findAllDirector());
        return "AlldirectorsView";
    }

    @GetMapping("/director/{id}/movies")
    public String getDirectorMoviesByID(Model model, @PathVariable("id") long id){
        Optional<Director> directorOpt = directorService.getDirectorById(id);
        Director director = directorOpt.get();
        model.addAttribute("director", director);
        model.addAttribute("movies", director.getMovies());
        model.addAttribute("genres", genreService.findAllGenre());
        return "directorMoviesView";
    }

}
