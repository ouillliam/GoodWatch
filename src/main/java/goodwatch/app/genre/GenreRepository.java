package goodwatch.app.genre;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    
    ArrayList<Genre> findAll();

    Optional<Genre> findByGenreID(Long id);
}
