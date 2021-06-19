package com.adesso.deneysiz.integration.repository;

import com.adesso.deneysiz.integration.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findBrandsByCategory(String categoryName);
    List<Brand> findAll();
}
