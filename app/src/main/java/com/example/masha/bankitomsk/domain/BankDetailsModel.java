package com.example.masha.bankitomsk.domain;

import android.os.AsyncTask;

import com.example.masha.bankitomsk.data.Bank;

/**
 * Created by masha on 04.04.2018.
 */

public class BankDetailsModel {


     public void getBankDetails(String url, BankDetailsCallback callback){

        BankDetailsTask getBankDetailsTask = new BankDetailsTask(url, callback);
        getBankDetailsTask.execute();
    }

    public interface BankDetailsCallback{
        public void getDetails(Bank bank);
    }

}
