package goodwatch.app.rating;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingPK> {
    
    public ArrayList<Rating> findAll();

}
