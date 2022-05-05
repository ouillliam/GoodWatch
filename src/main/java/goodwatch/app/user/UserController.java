package goodwatch.app.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ArrayList<User> getAllUsers(){
        return userService.findAllUser();
    }

}
