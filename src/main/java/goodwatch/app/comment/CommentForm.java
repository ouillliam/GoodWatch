package goodwatch.app.comment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentForm {
    
    private long movieID;

    @NotNull
    @Size(min=0, max=2000)
    private String content;
    
    public CommentForm(long movieID) {
        this.movieID = movieID;
    }

    public CommentForm() {
    }
    
    public long getMovieID() {
        return movieID;
    }
    public void setMovieID(long movieID) {
        this.movieID = movieID;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
}
