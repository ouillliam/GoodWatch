package goodwatch.app.rating;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RatingPK implements Serializable {
    
    @Column(name = "userid")
    private long userID;

    @Column(name = "movieid")
    private long movieID;

    public RatingPK() {
    }

    public RatingPK(long userID, long movieID) {
        this.userID = userID;
        this.movieID = movieID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, movieID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RatingPK other = (RatingPK) obj;
        if (movieID != other.movieID)
            return false;
        if (userID != other.userID)
            return false;
        return true;
    }
}
