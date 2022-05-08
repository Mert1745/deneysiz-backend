package com.adesso.deneysiz.integration.repository;

import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void findByNameContainingIgnoreCaseOrParentCompanyContainingIgnoreCase() {
        List<Brand> brandList = brandRepository.findByNameContainingIgnoreCaseOrParentCompanyContainingIgnoreCase("a", Category.ALL.getName());

        Assertions.assertThat(brandList).isNotEmpty();
    }

    @Test
    void findAll() {
        List<Brand> brandList = brandRepository.findAll();

        Assertions.assertThat(brandList).isNotEmpty();
    }
}