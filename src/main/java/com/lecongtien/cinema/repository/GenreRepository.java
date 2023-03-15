package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity,Integer> {
}
