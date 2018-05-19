package com.waffa.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waffa.entity.Category;
import com.waffa.entity.Items;

@Repository("categoryRepository")
public interface CategoryRepository  extends JpaRepository<Category, Integer>{
Category findOneCategoryById(int catId);
}
