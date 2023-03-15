package com.lecongtien.cinema.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "genre")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "genre_name")
    private String genreName;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "genre")
    private Set<GenreDetailEntity> genreDetail;

    public Set<GenreDetailEntity> getGenreDetail() {
        return genreDetail;
    }

    public void setGenreDetail(Set<GenreDetailEntity> genreDetail) {
        this.genreDetail = genreDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
