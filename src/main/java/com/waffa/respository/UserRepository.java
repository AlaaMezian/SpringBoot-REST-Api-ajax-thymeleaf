package com.waffa.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waffa.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUserEmail(String email);
	User findByUserName(String userName);
}