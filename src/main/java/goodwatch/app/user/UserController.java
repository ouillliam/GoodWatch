package goodwatch.app.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import goodwatch.app.movie.MovieService;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @GetMapping("/users")
    @ResponseBody
    public ArrayList<User> getAllUsers(){
        return userService.findAllUser();
    }

    @GetMapping("/admin/users")
    public String adminGetAllUsers(Model model){
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("userForm", new UserForm());
        return "adminAllUsersView";
    }

    @PostMapping("/admin/addUser")
    public String adminAddUser(@ModelAttribute("userForm") UserForm userForm){
        userService.saveUser(userForm);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/deleteUser/{id}")
    public String adminDeleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/addMovie/{id}")
    public String addToUserMovieList(HttpServletRequest request, @PathVariable("id") long movieID){
        userService.addMovie(movieID);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/user/removeMovie/{id}")
    public String removeFromUserMovieList(HttpServletRequest request, @PathVariable("id") long movieID){
        userService.removeMovie(movieID);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/user/watched")
    public String getAllMovies(Model model){
        model.addAttribute("movies", movieService.findAllByUserID(userService.getCurrentUser().getUserID()) ); 

        return "watchedView";
    }

    @GetMapping("/admin")
    public String displayAdminPage(){
        return "adminView";
    }

}
