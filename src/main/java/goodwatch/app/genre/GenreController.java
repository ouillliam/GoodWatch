package goodwatch.app.genre;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GenreController {
    
    @Autowired
    GenreService genreService;

    @GetMapping("/genres") 
    @ResponseBody
    public ArrayList<Genre> getAllGenre(){
        return genreService.findAllGenre();
    }

    @GetMapping("/genre/{id}/movies")
    public String getGenreMoviesById(Model model, @PathVariable("id") long id ){
        Optional<Genre> genreOpt = genreService.getGenreById(id);
        Genre genre = genreOpt.get();
        model.addAttribute("genre", genre);
        model.addAttribute("movies", genre.getMovies());
        model.addAttribute("genres", genreService.findAllGenre());
        return "genreMoviesView";
    }
}
