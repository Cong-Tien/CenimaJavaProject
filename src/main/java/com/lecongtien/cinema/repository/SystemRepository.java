package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.SystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<SystemEntity,Integer> {
    SystemEntity findByTenHtr(String name);
}
