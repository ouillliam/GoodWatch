package goodwatch.app.movie;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    ArrayList<Movie> findAll();

    ArrayList<Movie> findByDirectorDirectorID(long id);

    Optional<Movie> findByMovieID(long id);
    
}
