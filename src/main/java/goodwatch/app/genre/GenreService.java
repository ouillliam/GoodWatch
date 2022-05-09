package goodwatch.app.genre;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    
    @Autowired
    GenreRepository genreRepository;

    public ArrayList<Genre> findAllGenre(){
        return genreRepository.findAllByOrderByLabelAsc();
    }

    public Optional<Genre> getGenreById(Long id){
        return genreRepository.findByGenreID(id);
    }
}
