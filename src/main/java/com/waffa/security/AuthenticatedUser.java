package com.waffa.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.waffa.entity.User;

public class AuthenticatedUser implements UserDetails {

	private static final long serialVersionUID = 2245251454156719626L;


	private String username;
	private String password;
	private int id;
	private List<String> role;

	private User user;

	 @JsonCreator
	public AuthenticatedUser(@JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
		this.role = new ArrayList<String>();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String permissions = StringUtils.collectionToCommaDelimitedString(role);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(permissions);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// if(user.getIsActive().equals(Status.Y)) {
		return true;
		// }
		// return false;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "AuthenticatedUser [username=" + username + ", password=" + password + ", id=" + id + ", user=" + user
				+ "]";
	}

	@Override
	public String getUsername() {
		return username;
	}

}
