package com.example.masha.bankitomsk.domain;

import android.os.AsyncTask;
import android.util.Log;

import com.example.masha.bankitomsk.data.Bank;

import java.util.List;

/**
 * Created by masha on 20.03.2018.
 */

public class BanksModel {


    public void loadBanks(LoadBanksCallback callback){

        LoadBankTask loadBankTask = new LoadBankTask(callback);
        loadBankTask.execute();
    }

    public interface LoadBanksCallback{
       public void load(List<Bank> banks);
    }

}
