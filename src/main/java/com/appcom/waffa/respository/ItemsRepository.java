package com.appcom.waffa.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.entity.Category;
import com.appcom.waffa.entity.Items;

@Repository("itemRepository")
public interface ItemsRepository extends JpaRepository<Items, Integer>{
	public List<Items> findAllItemsByCategory(Category category);
	public Items findOneById(int itemId);
	@Query("SELECT i from Items i where i.id IN :ids")
	public List<Items> findAllItemsByIds( @Param ("ids") int ids [] );

}
