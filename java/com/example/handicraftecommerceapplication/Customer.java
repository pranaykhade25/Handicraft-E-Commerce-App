package com.example.handicraftecommerceapplication;

public class Customer {

    private String usr_full_name,usr_mail,pwd,confirm_pwd,gender;

    public Customer(String usr_full_name, String usr_mail, String pwd, String confirm_pwd, String gender) {
        this.usr_full_name = usr_full_name;
        this.usr_mail = usr_mail;
        this.pwd = pwd;
        this.confirm_pwd = confirm_pwd;
        this.gender = gender;
    }

    public String getUsr_full_name() {
        return usr_full_name;
    }

    public void setUsr_full_name(String usr_full_name) {
        this.usr_full_name = usr_full_name;
    }

    public String getUsr_mail() {
        return usr_mail;
    }

    public void setUsr_mail(String usr_mail) {
        this.usr_mail = usr_mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirm_pwd() {
        return confirm_pwd;
    }

    public void setConfirm_pwd(String confirm_pwd) {
        this.confirm_pwd = confirm_pwd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
