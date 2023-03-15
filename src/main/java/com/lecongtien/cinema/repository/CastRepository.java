package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.CastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepository extends JpaRepository<CastEntity,Integer> {
}
