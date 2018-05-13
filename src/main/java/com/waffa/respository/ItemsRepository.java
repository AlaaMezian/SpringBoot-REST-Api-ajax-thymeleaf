package com.waffa.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waffa.entity.Items;

@Repository("itemRepository")
public interface ItemsRepository extends JpaRepository<Items, Integer>{

	public List<Items> findAllByCategoryId(int categoryId);
}
