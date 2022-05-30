package goodwatch.app;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goodwatch.app.user.User;
import goodwatch.app.user.UserForm;
import goodwatch.app.user.UserService;

@Controller
public class HomeController {


    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public String displaySignUpForm(Model model, @PathParam("error") String error){
        model.addAttribute("userForm", new UserForm("", "", "user"));

        System.out.println(error);

        if(error == null){
            model.addAttribute("error", false);
        }
        else if (error.equals("1")){
            model.addAttribute("error", true);
        }

        try{
            if(userService.getCurrentUser().getRole() == "user"){
                return "redirect:/movies";
            }
            else if(userService.getCurrentUser().getRole() == "admin"){
                return "redirect:/admin";
            }
        }
        catch(Exception e){
            return "signUp";
        }

        return "signUp";
        
    }

    @PostMapping("/register")
    public String register(HttpServletRequest req, @ModelAttribute("userForm") UserForm userForm){

        System.out.println(userService.findByLogin(userForm.getLogin()).isPresent());
        if(userService.findByLogin(userForm.getLogin()).isPresent()){
            return "redirect:/?error=1";
        }

        User user = userService.saveUser(userForm);
        return "redirect:/movies";
    }
}
