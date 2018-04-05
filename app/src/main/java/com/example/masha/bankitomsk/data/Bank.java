package com.example.masha.bankitomsk.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masha on 20.03.2018.
 */

public class Bank {
    private String name;
    private String innerLink;
    private String webSite;
    private List<Office> offices = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInnerLink() {
        return innerLink;
    }

    public void setInnerLink(String innerLink) {
        this.innerLink = innerLink;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void addOffice(Office office) {
        offices.add(office);
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }

}
