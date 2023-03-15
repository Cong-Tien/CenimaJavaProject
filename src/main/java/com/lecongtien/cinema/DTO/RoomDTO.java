package com.lecongtien.cinema.DTO;

public class RoomDTO {
    private String roomName;
    private int totalSeat;
    private float avgTurnover;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public float getAvgTurnover() {
        return avgTurnover;
    }

    public void setAvgTurnover(float avgTurnover) {
        this.avgTurnover = avgTurnover;
    }
}
