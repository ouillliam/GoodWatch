package goodwatch.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @RequestMapping("/login")
    public String redirectLogin(){
        return "salut";
    }

    @RequestMapping("/hihi")
    public String lffl(){
        return "lofsfefsl";
    }

    @RequestMapping("/")
    public String getHome(){
        return "salut";
    }
}
