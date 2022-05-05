package goodwatch.app.rating;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {
    
    @Autowired
    RatingService ratingService;

    @GetMapping("/ratings")
    public ArrayList<Rating> getAllRatings(){
        return ratingService.findAllRating();
    }

}
