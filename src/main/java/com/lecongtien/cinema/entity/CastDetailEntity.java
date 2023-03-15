package com.lecongtien.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "ct_dienvien")
public class CastDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "id_phim")
    private MovieEntity movie;

    @ManyToOne()
    @JoinColumn(name = "id_dienvien")
    @JsonIgnore
    private CastEntity cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public CastEntity getCast() {
        return cast;
    }

    public void setCast(CastEntity cast) {
        this.cast = cast;
    }
}
