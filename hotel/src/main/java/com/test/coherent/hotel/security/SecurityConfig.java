package com.test.coherent.hotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	AuthenticationFilter authenticationFilter;
	
	 private static final String[] SWAGGER_WHITELIST = {
	            "/v3/api-docs/**",
	            "/swagger-ui/**",
	            "/swagger-ui.html",
	    };
	/*@Bean
	@Order(0)
	public SecurityFilterChain whitelistFilterChain(HttpSecurity http) throws Exception {
	    
	    return http
	      .securityMatcher(SWAGGER_WHITELIST)
	      .authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll())
	      .build();
	  }
	 */ 
	 
	 @Bean 
	 @Order(0)
	 public SecurityFilterChain resources(HttpSecurity http) throws Exception {
	     http
	     .securityMatchers((matchers) -> matchers.requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"),new AntPathRequestMatcher("/swagger-ui.html"),new AntPathRequestMatcher("/v3/api-docs/**")))
	         .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());

	     return http.build();
	 }
	//@Bean
   // public WebSecurityCustomizer webSecurityCustomizer() {
    //    return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"),new AntPathRequestMatcher("/swagger-ui.html"),new AntPathRequestMatcher("/v3/api-docs/**"));
    //}
	
    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	return http.csrf(csrf -> csrf.disable())
    		.securityMatchers((matchers) -> matchers.requestMatchers(new AntPathRequestMatcher("/newReservation")
    				,new AntPathRequestMatcher("/modifyReservation/*")
    				,new AntPathRequestMatcher("/reservation/*"),
    				new AntPathRequestMatcher("/reservations")))
    		.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
    		.authorizeHttpRequests((auth) -> auth
    		        .anyRequest().authenticated()
                )
    	.build();
    }
    
    
}
