package com.lecongtien.cinema.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "dien_vien")
public class CastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_dienvien")
    private String tenDienvien;
    @Column(name = "nam_sinh")
    private int namSinh;
    @Column(name = "ghi_chu")
    private String ghiChu;
    @Column(name = "danh_gia")
    private int danhGia;

    @OneToMany( mappedBy = "cast")
    private Set<CastDetailEntity> castDetail;

    public Set<CastDetailEntity> getCastDetail() {
        return castDetail;
    }

    public void setCastDetail(Set<CastDetailEntity> castDetail) {
        this.castDetail = castDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDienvien() {
        return tenDienvien;
    }

    public void setTenDienvien(String tenDienvien) {
        this.tenDienvien = tenDienvien;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }
}
