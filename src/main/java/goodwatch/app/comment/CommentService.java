package goodwatch.app.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import goodwatch.app.movie.Movie;
import goodwatch.app.movie.MovieRepository;
import goodwatch.app.user.User;
import goodwatch.app.user.UserDetailsImp;

@Service
public class CommentService {
    
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MovieRepository movieRepository;

    public void saveComment(CommentForm commentForm){
        Movie movie = this.movieRepository.getById(commentForm.getMovieID());
        User user = ((UserDetailsImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Comment comment = new Comment(user, movie, commentForm.getContent());
        this.commentRepository.save(comment);
    }
}
