package goodwatch.app.rating;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RatingController {
    
    @Autowired
    RatingService ratingService;

    @PostMapping("/rating")
    public String saveRating(@ModelAttribute("ratingForm") RatingForm ratingForm){

        this.ratingService.saveRating(ratingForm);
        return "redirect:movie/" + ratingForm.getMovieID();
    }

}
