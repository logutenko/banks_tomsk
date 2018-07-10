package com.example.masha.bankitomsk.domain;

import com.example.masha.bankitomsk.data.Bank;

import java.util.List;

/**
 * Created by masha on 20.03.2018.
 */

public class BanksModel {

    private String url;

    public BanksModel(String url) {
        this.url = url;
    }


    public void loadBanks(LoadBanksCallback callback) {

        LoadBankTask loadBankTask = new LoadBankTask(callback, url);
        loadBankTask.execute();
    }

    public interface LoadBanksCallback {
        void load(List<Bank> banks);
    }

}
