package goodwatch.app.movie;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    @Autowired
    MovieRepository movieRepository;

    public ArrayList<Movie> findAllMovie(){
        return movieRepository.findAll();
    }
}
