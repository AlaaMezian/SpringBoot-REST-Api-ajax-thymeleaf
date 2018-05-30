package com.appcom.waffa.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appcom.waffa.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser,Integer>{
   
	AdminUser findByUserEmail(String email);
}
