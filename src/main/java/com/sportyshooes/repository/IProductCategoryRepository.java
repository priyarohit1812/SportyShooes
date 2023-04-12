package com.sportyshooes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportyshooes.model.ProductCategory;

public interface IProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	List<ProductCategory> findByCategoryNameContainsOrTargetGroupContains(String categoryName, String targetGroup);
	Optional<ProductCategory> findByCategoryName(String categoryName);
	List<ProductCategory> findByTargetGroup(String targetGroup);
	
	@Query("SELECT DISTINCT(pc.targetGroup) FROM ProductCategory pc")
	List<String> getDistictTargetGroups();
	
	@Query("SELECT DISTINCT(pc.categoryName) FROM ProductCategory pc")
	List<String> getDistictCategoryName();
}
