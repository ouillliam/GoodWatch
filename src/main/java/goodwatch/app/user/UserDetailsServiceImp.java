package goodwatch.app.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        
        Optional<User> userOpt = userRepository.findByLogin(username);

        if( userOpt.isEmpty() ){
            throw new UsernameNotFoundException("Login does not match any account");
        }

        return new UserDetailsImp(userOpt.get());
    }
    
}
