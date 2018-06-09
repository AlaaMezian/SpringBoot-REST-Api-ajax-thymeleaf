package com.appcom.waffa.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository  extends JpaRepository<Category, Integer>{
Category findOneCategoryById(int catId);
List<Category> findAllByisActive(Status status);
}
