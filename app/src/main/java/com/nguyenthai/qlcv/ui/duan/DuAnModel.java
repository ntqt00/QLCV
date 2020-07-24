package com.nguyenthai.qlcv.ui.duan;

public class DuAnModel {

    private int id;
    private String ten_du_an;

    public DuAnModel(int id, String ten_du_an) {
        this.id = id;
        this.ten_du_an = ten_du_an;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_du_an() {
        return ten_du_an;
    }

    public void setTen_du_an(String ten_du_an) {
        this.ten_du_an = ten_du_an;
    }
}
