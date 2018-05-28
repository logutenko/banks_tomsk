package com.example.masha.bankitomsk.data;

/**
 * Created by masha on 03.05.2018.
 */

public class Office {
    private String address;
    private String phone;

    public Office(String address, String phone){
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
