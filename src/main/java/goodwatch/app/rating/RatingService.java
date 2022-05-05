package goodwatch.app.rating;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    
    @Autowired
    RatingRepository ratingRepository;

    public ArrayList<Rating> findAllRating(){
        return ratingRepository.findAll();
    }

}
