package com.example.handicraftecommerceapplication;

public class PotteryModel {

    private String pottery_name;
    private int imgid;

    public PotteryModel(String pottery_name, int imgid) {
        this.pottery_name = pottery_name;
        this.imgid = imgid;
    }

    public String getCourse_name() {
        return pottery_name;
    }

    public void setCourse_name(String pottery_name) {
        this.pottery_name = pottery_name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}

