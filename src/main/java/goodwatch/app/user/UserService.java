package goodwatch.app.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import goodwatch.app.movie.Movie;
import goodwatch.app.movie.MovieRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    public ArrayList<User> findAllUser(){
        return userRepository.findAll();
    } 
    

    public User getCurrentUser(){
        return ((UserDetailsImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

  
    public User saveUser(UserForm userForm){
        String password = passwordEncoder.encode(userForm.getPassword());
        return userRepository.save(new User(userForm.getLogin(), password, userForm.getRole()));
    }

    public void deleteUser(long userID){
        userRepository.deleteById(userID);
    }
    
    public Optional<User> findByLogin(String login){
        return userRepository.findByLogin(login);
    }

   //@Transactional
    public void addMovie(long movieID){
        User user = ((UserDetailsImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        List<Movie> userMovies = movieRepository.findByUsersUserID(user.getUserID());
        userMovies.add(movieRepository.getById(movieID));
        user.setMovies(userMovies);
        userRepository.save(user);
        // User managedUser = em.find(User.class, user.getUserID());
        // em.refresh(em.merge(managedUser));
        

    }

    public void removeMovie(long movieID){
        User user = ((UserDetailsImp)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        List<Movie> userMovies = movieRepository.findByUsersUserID(user.getUserID());
        Movie toRemove = new Movie();
        for (Movie movie : userMovies) {
            if(movie.getMovieID() == movieID){
                toRemove = movie;
            }
        }
        userMovies.remove(toRemove);
        user.setMovies(userMovies);
        userRepository.save(user);
    }

}
