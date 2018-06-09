package com.appcom.waffa.config;

import static com.appcom.waffa.utils.AppConstants.FORGET_URL;
import static com.appcom.waffa.utils.AppConstants.LOGIN_URL;
import static com.appcom.waffa.utils.AppConstants.SIGN_UP_URL;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.appcom.waffa.respository.UserRepository;
import com.appcom.waffa.security.JWTAuthenticationFilter;
import com.appcom.waffa.security.JWTAuthorizationFilter;
import com.appcom.waffa.security.UserDetailsServiceImpl;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final String USERS_QUERY = "select user_name,user_email, enpassword, is_active from admin_user where user_email=?";


	public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
     	auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//		auth.jdbcAuthentication().usersByUsernameQuery(USERS_QUERY)
//		.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
//		.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll().antMatchers(HttpMethod.POST, FORGET_URL).permitAll().anyRequest().authenticated().and()
//		.addFilterBefore(new CORSFilter(),ChannelProcessingFilter.class)
//		.addFilter(new JWTAuthenticationFilter(authenticationManager(),userRepository))
//		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
//		// this disables session creation on Spring Security
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
//	
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
		.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll().antMatchers(HttpMethod.POST, FORGET_URL).permitAll()
		//web
		.antMatchers("/*").permitAll().antMatchers("/login").permitAll().antMatchers("/signup").permitAll()
		.antMatchers("/home/**").permitAll()
		.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/scss/**","/fonts/**","/*").permitAll()
		.anyRequest().permitAll()

		
		
		.anyRequest().authenticated().and()
		
		.addFilterBefore(new CORSFilter(),ChannelProcessingFilter.class)
		.addFilter(new JWTAuthenticationFilter(authenticationManager(),userRepository))
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
//		// this disables session creation on Spring Security
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	 
	 @Override
	 public void configure(WebSecurity web) throws Exception {
		  web
          .ignoring()
          .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/scss/**","/fonts/**","/*");
	 }



}