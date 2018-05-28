package com.example.masha.bankitomsk.data;

/**
 * Created by masha on 04.04.2018.
 */

public class Currency {
    private String name;
    private String rateToSell;
    private String rateToBuy;

    public Currency(String name, String rateToBuy, String rateToSell) {
        this.name = name;
        this.rateToBuy = rateToBuy;
        this.rateToSell = rateToSell;
    }

    public String getName() {
        return name;
    }

    public String getRateToSell() {
        return rateToSell;
    }

    public void setRateToSell(String rateToSell) {
        this.rateToSell = rateToSell;
    }

    public String getRateToBuy() {
        return rateToBuy;
    }

    public void setRateToBuy(String rateToBuy) {
        this.rateToBuy = rateToBuy;
    }
}
