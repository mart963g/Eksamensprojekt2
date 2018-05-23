package enggaarden.app.Security;

import enggaarden.app.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Inspired by https://o7planning.org/en/10603/spring-mvc-security-and-spring-jdbc-tutorial


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    /*
    Fields
     */
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // Hashing
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // Restrict user access
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();

        //Free pages
        http.authorizeRequests().antMatchers("/login","/logout","/fail").permitAll();

        //Can be accessed by both
        http.authorizeRequests().antMatchers("/home","/","/contributions*", "/subLogs*", "/emails", "/statistics",
                "/members*").access("hasAnyAuthority('Administrator','Standard')");

        //For admin only
        http.authorizeRequests().antMatchers("/users*", "/reset").access("hasAuthority('Administrator')");

        //Acces denied page
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/fail");

        //Login config
        http.authorizeRequests().and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/fail")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    // Creates an instance of authority based on user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
