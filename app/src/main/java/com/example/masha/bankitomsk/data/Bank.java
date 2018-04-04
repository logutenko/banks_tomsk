package com.example.masha.bankitomsk.data;

import java.util.List;
import java.util.Map;

/**
 * Created by masha on 20.03.2018.
 */

public class Bank {
    private String name;
    private String link;
    private List<String> address;
    private List<String> phones;
    private List<Currency> currencies;
//    private Map<String,String> rateToBuy;
//    private Map<String,String> rateToSell;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public  String []getAddress() {
        return address;
    }

    public void setAddress(String [] address) {
        this.address = address;
    }

    public String [] getPhone() {
        return phone;
    }

    public void setPhone(String [] phone) {
        this.phone = phone;
    }

    public Map<String,String> getRateToBuy() {
        return rateToBuy;
    }

    public void setRateToBuy(Map<String,String> rateToBuy) {
        this.rateToBuy = rateToBuy;
    }

    public Map<String,String> getRateToSell() {
        return rateToSell;
    }

    public void setRateToSell(Map<String,String> rateToSell) {
        this.rateToSell = rateToSell;
    }
}
