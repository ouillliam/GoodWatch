package goodwatch.app.movie;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/test")
    @ResponseBody
    public ArrayList<Movie> test(){
        return movieService.findAllMovie();
    }

    @GetMapping("/movies")
    public String getAllMovies(Model model){
        model.addAttribute("movies",movieService.findAllMovie()); 
        return "allMoviesView";
    }
    
}
