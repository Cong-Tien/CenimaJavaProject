package com.lecongtien.cinema.entity.id;

import javax.persistence.Id;
import java.io.Serializable;

public class GenreDetailId implements Serializable {
    private int id_phim;
    private int id_theloai;
    public GenreDetailId(int id_phim,int id_theloai){
        this.id_phim=id_phim;
        this.id_theloai=id_theloai;
    }

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
}
