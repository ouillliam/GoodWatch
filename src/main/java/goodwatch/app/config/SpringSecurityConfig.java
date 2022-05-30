package goodwatch.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import goodwatch.app.user.UserDetailsServiceImp;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    
    @Bean 
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImp();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/movies").hasRole("user")
            .antMatchers("/movie/{id}").hasRole("user")
            .antMatchers("/movie/search").hasRole("user")
            .antMatchers("/directors").hasRole("user")
            .antMatchers("/director/{id}/movies").hasRole("user")
            .antMatchers("/genre/{id}/movies").hasRole("user")
            .antMatchers("/comment").hasRole("user")
            .antMatchers("/user/addMovie/{id}").hasRole("user")
            .antMatchers("/user/removeMovie/{id}").hasRole("user")
            .antMatchers("/user/watched").hasRole("user")
            .antMatchers("/admin/**").hasRole("admin")
            .and()
            .formLogin()
            .successHandler(loginSuccessHandler)
            .permitAll()
            .and()
            .logout().permitAll();
    }

}
    



