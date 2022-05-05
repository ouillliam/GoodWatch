package goodwatch.app.genre;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {
    
    @Autowired
    GenreService genreService;

    @GetMapping("/genres") 
    public ArrayList<Genre> getAllGenre(){
        return genreService.findAllGenre();
    }

    @GetMapping("/genre/{id}")
    public Genre getGenreById(@PathVariable("id") long id ){
        return genreService.getGenreById(id).get();
    }
}
