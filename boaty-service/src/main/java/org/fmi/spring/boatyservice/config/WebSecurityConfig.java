package org.fmi.spring.boatyservice.config;

import org.fmi.spring.boatyservice.security.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling()
            .and()
                .authorizeRequests()
            // Users
                .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/users/*").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/users/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/tokens").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/*/top-up").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/*").authenticated()
            // Zones
                .antMatchers(HttpMethod.GET, "/api/zones").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/zones").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/zones/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/zones/*").hasRole("ADMIN")
            // Tickets
                .antMatchers(HttpMethod.GET, "/api/tickets").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/tickets/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/tickets").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/tickets/*").hasRole("ADMIN")
            // CardDetails
                .antMatchers(HttpMethod.GET, "/api/payments/*/cards").authenticated()
                .antMatchers(HttpMethod.POST, "/api/payments/*/cards").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/payments/*/cards/*").authenticated()
            // Stations
                .antMatchers(HttpMethod.GET, "/api/stations").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/stations/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/stations").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/stations/*").hasRole("ADMIN")
            // Lines
                .antMatchers(HttpMethod.GET, "/api/lines").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/lines/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/lines").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/lines/*").hasRole("ADMIN")
            // Purchase
            .antMatchers(HttpMethod.GET, "/api/purchase/ticket/*").authenticated()
            .antMatchers(HttpMethod.POST, "/api/purchase/ticket/*").hasRole("ADMIN")

            .and()
                .httpBasic()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtTokenAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
