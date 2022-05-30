package goodwatch.app.rating;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RatingForm {
    
    private long movieID;

    @Min(0)
    @Max(5)
    private int rating;

    public RatingForm(long movieID, int rating) {
        this.movieID = movieID;
        this.rating = rating;
    }

    public RatingForm() {
    }

    public long getMovieID() {
        return movieID;
    }

    public void setMovieID(long movieID) {
        this.movieID = movieID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
