package goodwatch.app.movie;

import java.util.List;

public class MovieForm {
    
    private String title;
    private int releaseYear;
    private String plot;
    private String poster = "poster.jpg";
    private int runtime;
    private long directorID;
    private List<String> genres;

    public MovieForm(String title, int releaseYear, String plot, String poster, int runtime, long directorID,
            List<String> genres) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.plot = plot;
        this.poster = "poster.jpg";
        this.runtime = runtime;
        this.directorID = directorID;
        this.genres = genres;
    }
    public MovieForm() {
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public String getPlot() {
        return plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public long getDirectorID() {
        return directorID;
    }
    public void setDirectorID(long directorID) {
        this.directorID = directorID;
    }
 
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
