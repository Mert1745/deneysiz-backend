package com.adesso.deneysiz.admin.repository;

import com.adesso.deneysiz.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUserName(String userName);
}
