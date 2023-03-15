package com.lecongtien.cinema.entity;

import javax.persistence.*;

@Entity(name = "he_thong_rap")
public class SystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHtr;
    @Column(name = "ten_htr")
    private String tenHtr;
    @Column(name = "logo")
    private String logo;
    @Column(name = "hot_call")
    private int hotCall;
    @Column(name = "khu_vuc")
    private String khuVuc;

    public int getMaHtr() {
        return maHtr;
    }

    public void setMaHtr(int maHtr) {
        this.maHtr = maHtr;
    }

    public String getTenHtr() {
        return tenHtr;
    }

    public void setTenHtr(String tenHtr) {
        this.tenHtr = tenHtr;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getHotCall() {
        return hotCall;
    }

    public void setHotCall(int hotCall) {
        this.hotCall = hotCall;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }
}
