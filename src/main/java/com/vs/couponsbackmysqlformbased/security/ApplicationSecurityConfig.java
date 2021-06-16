// *******************************************
// FormBase Authentication
// *******************************************
package com.vs.couponsbackmysqlformbased.security;

import com.vs.couponsbackmysqlformbased.enums.ClientType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //to enable @PreAuthorized on each method separately
//on method @PreAuthorize("hasRole('ROLE_Administrator')") //individual authorization
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthProvider());
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("a"))
                .roles(ClientType.ADMINISTRATOR.toString());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /*.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//to avoid cross site request forgery
            .and()*/
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login").permitAll() //don't need authentication
                .antMatchers("/companies/**", "/categories/**", "/customers/**").hasRole(ClientType.ADMINISTRATOR.toString())
                .antMatchers("/coupons/**").hasRole(ClientType.COMPANY.toString())
                .antMatchers("/purchases/**").hasRole(ClientType.CUSTOMER.toString())
                .anyRequest()
                .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home", true)
                .passwordParameter("password") //login.html row 27
                .usernameParameter("username") //login.html row 23
            .and()
            .rememberMe() //default to 2 weeks
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1))
                .key("somethingverysecure")
                .rememberMeParameter("remember-me") //login.html row 30
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //only if csrf is disabled
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me") //login.html row 30
                .logoutSuccessUrl("/login");
    }

    @Bean
    public DaoAuthenticationProvider userAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userAuthenticationService);
        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "https://60c327b06aa6b000087195b1--confident-booth-9b9e89.netlify.app/",
                "http://localhost:3000"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}