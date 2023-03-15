package com.lecongtien.cinema.entity;

import javax.persistence.*;

@Entity(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRoom;
    @Column(name = "name_room")
    private String nameRoom;
    @Column(name = "total_seat")
    private int totalSeat;
    @Column(name = "status")
    private int status;
    @Column(name = "row_seat")
    private int rowSeat;
    @Column(name = "columne_seat")
    private int columneSeat;
    @Column(name = "id_cinema")
    private int idCinema;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRowSeat() {
        return rowSeat;
    }

    public void setRowSeat(int rowSeat) {
        this.rowSeat = rowSeat;
    }

    public int getColumneSeat() {
        return columneSeat;
    }

    public void setColumneSeat(int columneSeat) {
        this.columneSeat = columneSeat;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }
}
