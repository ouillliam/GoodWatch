package goodwatch.app.genre;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    
    ArrayList<Genre> findAllByOrderByLabelAsc();

    Optional<Genre> findByGenreID(Long id);
}
