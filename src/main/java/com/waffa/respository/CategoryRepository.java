package com.waffa.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waffa.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository  extends JpaRepository<Category, Integer>{

}
