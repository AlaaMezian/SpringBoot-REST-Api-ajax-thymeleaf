package com.waffa.config;

import static com.waffa.utils.AppConstants.LOGIN_URL;
import static com.waffa.utils.AppConstants.SIGN_UP_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.waffa.security.JWTAuthenticationFilter;
import com.waffa.security.JWTAuthorizationFilter;
import com.waffa.security.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
				.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll().anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	//
	// private final String USERS_QUERY = "select user_name,user_email
	// ,enc_password, is_active from user where user_email=?";
	//
	// @Autowired
	// private RestAuthenticationEntryPoint authenticationEntryPoint;
	//
	// @Autowired
	// private BCryptPasswordEncoder bCryptPasswordEncoder;
	//
	// @Autowired
	// private DataSource dataSource;
	//
	// @Override
	// public void configure(final WebSecurity web) throws Exception {
	// web.ignoring().antMatchers("/v1/api", "/**");
	// }
	//
	// @Override
	// public void configure(HttpSecurity http) throws Exception {
	// http.csrf().disable().authorizeRequests().antMatchers("/v1/api",
	// "/**").permitAll().anyRequest().authenticated()
	// .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
	// .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	// }
	//
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// auth.jdbcAuthentication().usersByUsernameQuery(USERS_QUERY).dataSource(dataSource)
	// .passwordEncoder(bCryptPasswordEncoder);
	// }
	//
	// @Bean
	// public RestAuthenticationEntryPoint getBasicAuthEntryPoint() {
	// return new RestAuthenticationEntryPoint();
	// }

}