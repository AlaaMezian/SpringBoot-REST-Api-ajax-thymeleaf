package com.appcom.waffa.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {

	Token findOneByToken(String token);
	
	
}
