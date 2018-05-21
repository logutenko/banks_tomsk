package com.example.masha.bankitomsk.domain;

import android.os.AsyncTask;
import android.util.Log;

import com.example.masha.bankitomsk.data.Bank;

import java.util.List;

/**
 * Created by masha on 15.05.2018.
 */

class LoadBankTask extends AsyncTask<Void, Void, List<Bank>> {

    private final BanksModel.LoadBanksCallback callback;

    LoadBankTask(BanksModel.LoadBanksCallback callback){
        this.callback = callback;
    }
    @Override
    protected List<Bank> doInBackground(Void... params) {

        HTMLParser htmlParser = new HTMLParser("http://banki.tomsk.ru/pages/41/");
        List<Bank> banksList = htmlParser.parseBanks();
        return banksList;
    }

    protected void onPostExecute(List<Bank> result) {
        super.onPostExecute(result);
        callback.load(result);
    }
}