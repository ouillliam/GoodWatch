package goodwatch.app.config;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import goodwatch.app.user.UserDetailsImp;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
         
        System.out.println(userDetailsImp.getUser().getRole());
        if (userDetailsImp.getUser().getRole().compareTo("user") == 0) {
            redirectURL = "movies";
            
        } else if (userDetailsImp.getUser().getRole().compareTo("admin") == 0) {
            redirectURL = "admin/movies";
        }
         
        
        response.sendRedirect(redirectURL);
         
    }
 
}