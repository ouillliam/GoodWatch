package goodwatch.app.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import goodwatch.app.comment.Comment;
import goodwatch.app.comment.CommentRepository;
import goodwatch.app.director.Director;
import goodwatch.app.director.DirectorRepository;
import goodwatch.app.genre.Genre;
import goodwatch.app.genre.GenreRepository;
import goodwatch.app.rating.Rating;
import goodwatch.app.rating.RatingRepository;
import goodwatch.app.user.User;
import goodwatch.app.user.UserDetailsImp;
import goodwatch.app.user.UserRepository;

@Service
public class MovieService {
    
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public ArrayList<Movie> findAllMovie(){
        ArrayList<Movie> movies = movieRepository.findAll();
        for (Movie movie : movies) {
            movie.setWatched(this.isInWatched(movie.getMovieID()));
        }
        return movies;
    }

    public ArrayList<Movie> findAllMovieByDirectorID(long id){
        ArrayList<Movie> movies =  movieRepository.findByDirectorDirectorID(id);
        for (Movie movie : movies) {
            movie.setWatched(this.isInWatched(movie.getMovieID()));
        }
        return movies;
    }

    public Optional<Movie> getMovieByID(long id){
        Movie movie = null;
        if(movieRepository.findByMovieID(id).isPresent()){
            movie = movieRepository.findByMovieID(id).get();
            movie.setWatched(this.isInWatched(movie.getMovieID()));
        }
        
        return Optional.ofNullable(movie);
    }

    public ArrayList<Movie> findAllByUserID(long id){
        ArrayList<Movie> movies = movieRepository.findByUsersUserID(id);
        for (Movie movie : movies) {
            movie.setWatched(this.isInWatched(movie.getMovieID()));
        }
        return movies;
    }

    public boolean isInWatched(long movieID){
        User user = ((UserDetailsImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        for (Movie movie : movieRepository.findByUsersUserID(user.getUserID())) {
            if(movie.getMovieID() == movieID){
                return true;
                
            }
        }
        return false;
    }

    public Movie saveMovie(MovieForm movieForm){
        Director director = directorRepository.getById(movieForm.getDirectorID());
        ArrayList<Genre> genres = new ArrayList<>();
        for (String label : movieForm.getGenres()) {
            genres.add(genreRepository.findByLabel(label));
        }
        Movie movie = new Movie(movieForm.getTitle(), movieForm.getReleaseYear(), movieForm.getPlot(), movieForm.getPoster(), movieForm.getRuntime(), director, genres);
        movieRepository.save(movie);
        return movie;
    }

    public void editMovie(long movieID, MovieForm movieForm){
        Director director = directorRepository.getById(movieForm.getDirectorID());
        ArrayList<Genre> genres = new ArrayList<>();
        for (String label : movieForm.getGenres()) {
            genres.add(genreRepository.findByLabel(label));
        }

        Movie movie = movieRepository.getById(movieID);

        movie.setDirector(director);
        movie.setGenres(genres);
        movie.setPlot(movieForm.getPlot());
        movie.setReleaseYear(movieForm.getReleaseYear());
        movie.setPoster(movieForm.getPoster());
        movie.setTitle(movieForm.getTitle());
        movie.setRuntime(movieForm.getRuntime());

        movieRepository.save(movie);

        
    }

    public void deleteMovie(long movieID){
        System.out.println(movieID);
        ArrayList<Rating> ratings = ratingRepository.findAllByIdMovieID(movieID);
        System.out.println(ratings);
        for(Rating r : ratings){
            ratingRepository.delete(r);
        }
        Movie movie = movieRepository.getById(movieID);
        movie.setUsers(new ArrayList<User>());
        movie.setComments(new ArrayList<Comment>());
        movieRepository.deleteById(movieID);
    }

    public ArrayList<Movie> findBySearch(String search){
        return movieRepository.findBySearch(search);
    }
    
}
