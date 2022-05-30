package goodwatch.app.rating;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingPK> {
    
    public ArrayList<Rating> findAll();

    public Optional<Rating> findByIdUserIDAndIdMovieID(long userID, long movieID);

    public ArrayList<Rating> findAllByIdMovieID(long movieID);
}
