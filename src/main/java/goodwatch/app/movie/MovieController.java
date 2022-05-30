package goodwatch.app.movie;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import goodwatch.app.comment.Comment;
import goodwatch.app.comment.CommentForm;
import goodwatch.app.director.DirectorService;
import goodwatch.app.genre.Genre;
import goodwatch.app.genre.GenreService;
import goodwatch.app.rating.Rating;
import goodwatch.app.rating.RatingForm;
import goodwatch.app.rating.RatingService;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired 
    GenreService genreService;

    @Autowired
    RatingService ratingService;

    @Autowired
    DirectorService directorService;

    @GetMapping("/test")
    @ResponseBody
    public ArrayList<Movie> test(){
        return movieService.findAllMovie();
    }

    @GetMapping("/admin/movies")
    public String adminGetAllMovies(Model model){
        model.addAttribute("movies",movieService.findAllMovie()); 
        model.addAttribute("movieForm", new MovieForm()); 
        model.addAttribute("directors", directorService.findAllDirector());
        model.addAttribute("genres", genreService.findAllGenre());
        return "adminAllMoviesView";
    }

    @GetMapping("/admin/movie/{id}")
    public String adminGetAllMovies(Model model, @PathVariable("id") long id){
        Movie movie = movieService.getMovieByID(id).get();
        MovieForm movieForm = new MovieForm();
        movieForm.setTitle(movie.getTitle());
        movieForm.setReleaseYear(movie.getReleaseYear());
        movieForm.setRuntime(movie.getRuntime());
        movieForm.setPlot(movie.getPlot());
        movieForm.setDirectorID(movie.getDirector().getDirectorID());

        List<String> genreLabels = new ArrayList<String>();

        for(Genre genre : movie.getGenres()){
            genreLabels.add(genre.getLabel());
        }

        movieForm.setGenres(genreLabels);

        model.addAttribute("movieForm", movieForm); 
        model.addAttribute("directors", directorService.findAllDirector());
        model.addAttribute("genres", genreService.findAllGenre());
        model.addAttribute("movieID", id);

        return "adminEditMovieView";
    }

    @PostMapping("admin/editMovie/{id}")
    public String editMovie(@PathVariable("id") long id, @ModelAttribute("movieForm") MovieForm movieForm){
        
        movieService.editMovie(id , movieForm);

        return "redirect:/admin/movie/" + id;
    }

    @PostMapping("admin/deleteMovie/{id}")
    public String delelteMovie(@PathVariable("id") long id){
        
        movieService.deleteMovie(id);

        return "redirect:/admin/movies/";
    }


    
    @GetMapping("/movies")
    public String getAllMovies(Model model){
        model.addAttribute("movies",movieService.findAllMovie()); 
        model.addAttribute("genres", genreService.findAllGenre());
        return "allMoviesView";
    }

    @GetMapping("/movie/{id}")
    public String getAllMovies(Model model, @PathVariable("id") long id){

        if(movieService.getMovieByID(id).isEmpty()){
            return "noMatch";
        }

        model.addAttribute("movie",movieService.getMovieByID(id).get());
        model.addAttribute("commentForm", new CommentForm(id)); 

        Optional<Rating> rating = ratingService.getByUserIDAndMovieID(id);
        RatingForm ratingForm = new RatingForm();

        if(rating.isPresent()){
            ratingForm.setMovieID(rating.get().getMovie().getMovieID());
            ratingForm.setRating(rating.get().getRating());
        }

        model.addAttribute("ratingForm", ratingForm);


        return "singleMovieView";
    }

    @PostMapping("/admin/addMovie")
    public String addMovie(@ModelAttribute("movieForm") MovieForm movieForm){
        Movie movie = movieService.saveMovie(movieForm);
        return "redirect:/admin/movies";
    }

    @GetMapping("/movies/search")
    public String searchMovies(Model model, @RequestParam(value = "search") String search){
        ArrayList<Movie> movies = movieService.findBySearch(search);
        model.addAttribute("movies", movies);
        model.addAttribute("search", search);
        return "searchMoviesView";
    }
    
}
