package com.lecongtien.cinema.entity;

import com.lecongtien.cinema.entity.id.GenreDetailId;

import javax.persistence.*;

@Entity(name = "genre_detail")
@IdClass(GenreDetailId.class)
public class GenreDetailEntity {
    @Id
    private int id_phim;
    @Id
    private int id_theloai;

    @ManyToOne()
    @JoinColumn(name = "id_phim",insertable = false,updatable = false)
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "id_theloai", insertable = false,updatable = false)
    private GenreEntity genre;

    public int getId_phim() {
        return id_phim;
    }

    public void setId_phim(int id_phim) {
        this.id_phim = id_phim;
    }

    public int getId_theloai() {
        return id_theloai;
    }

    public void setId_theloai(int id_theloai) {
        this.id_theloai = id_theloai;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }
}
