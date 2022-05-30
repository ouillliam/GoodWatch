package goodwatch.app.director;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("admin/directors")
    public String adminGetAllDirectors(Model model){
        model.addAttribute("directors", directorService.findAllDirector());
        model.addAttribute("directorForm", new DirectorForm());
        return "adminAlldirectorsView";
    }

    @PostMapping("admin/addDirector")
    public String adminAddDirector(@ModelAttribute("directorForm") DirectorForm df){
        Director director = new Director(df.getName());
        directorService.saveDirector(director);
        return "redirect:/admin/directors";
    }

    @GetMapping("admin/deleteDirector/{id}")
    public String adminAddDirector(@PathVariable("id") long id){
        directorService.deleteDirector(id);
        return "redirect:/admin/directors";
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
