package com.appcom.waffa.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUserEmail(String email);
	User findByUsername(String userName);
	User findById(int id);
}