package goodwatch.app.movie;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    ArrayList<Movie> findAll();

    ArrayList<Movie> findByDirectorDirectorID(long id);

    Optional<Movie> findByMovieID(long id);

    ArrayList<Movie> findByUsersUserID(long id);

    @Query(value = "select * from movie m join director d on d.directorID = m.directorID where m.title like %:search% or d.name like %:search%", nativeQuery = true)
    ArrayList<Movie> findBySearch(@Param("search") String search);
    
}
