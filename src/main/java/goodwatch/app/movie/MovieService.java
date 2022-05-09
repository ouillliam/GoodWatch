package goodwatch.app.movie;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    @Autowired
    MovieRepository movieRepository;

    public ArrayList<Movie> findAllMovie(){
        return movieRepository.findAll();
    }

    public ArrayList<Movie> findAllMovieByDirectorID(long id){
        return movieRepository.findByDirectorDirectorID(id);
    }

    public Optional<Movie> getMovieByID(long id){
        return movieRepository.findByMovieID(id);
    }
}
