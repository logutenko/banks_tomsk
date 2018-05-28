package com.example.masha.bankitomsk.domain;

import com.example.masha.bankitomsk.data.Bank;

/**
 * Created by masha on 04.04.2018.
 */

public class BankDetailsModel {


    public void getBankDetails(String url, BankDetailsCallback callback) {

        BankDetailsTask getBankDetailsTask = new BankDetailsTask(url, callback);
        getBankDetailsTask.execute();
    }

    public interface BankDetailsCallback {
        void getDetails(Bank bank);
    }

}
