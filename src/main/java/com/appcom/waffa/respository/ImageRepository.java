package com.appcom.waffa.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.entity.Images;

@Repository("imageRespository")
public interface ImageRepository extends JpaRepository<Images, Long>{
	
}
