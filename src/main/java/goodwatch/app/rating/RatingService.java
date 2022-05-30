package goodwatch.app.rating;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import goodwatch.app.movie.Movie;
import goodwatch.app.movie.MovieRepository;
import goodwatch.app.movie.MovieService;
import goodwatch.app.user.User;
import goodwatch.app.user.UserDetailsImp;

@Service
public class RatingService {
    
    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    MovieService movieService;

    public ArrayList<Rating> findAllRating(){
        return ratingRepository.findAll();
    }

    public ArrayList<Rating> findAllByMovieID(long movieID){
        return ratingRepository.findAllByIdMovieID(movieID);
    }

    public Optional<Rating> getByUserIDAndMovieID(long movieID){

        long userID = ((UserDetailsImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserID();

        return ratingRepository.findByIdUserIDAndIdMovieID(userID, movieID);
    }

    public void saveRating(RatingForm ratingForm){

        Movie movie = movieService.getMovieByID(ratingForm.getMovieID()).get();
        User user = ((UserDetailsImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Rating rating;
        Optional<Rating> ratingOpt = ratingRepository.findByIdUserIDAndIdMovieID(user.getUserID(), movie.getMovieID());

        if(ratingOpt.isPresent()){
            rating = ratingOpt.get();
            rating.setRating(ratingForm.getRating());
        }
        else{
            rating = new Rating(user, movie, ratingForm.getRating());
        }

        ratingRepository.save(rating);
    }

}
