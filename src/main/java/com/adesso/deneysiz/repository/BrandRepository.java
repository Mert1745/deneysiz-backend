package com.adesso.deneysiz.repository;

import com.adesso.deneysiz.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findBrandsById(Long id);

    //TODO mkose consider pagination
    Page<Brand> findAll(Pageable pageable);
}
