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
    private final String url;

    LoadBankTask(BanksModel.LoadBanksCallback callback, String url){
        this.callback = callback;
        this.url = url;
    }
    @Override
    protected List<Bank> doInBackground(Void... params) {

        HTMLParser htmlParser = new HTMLParser(url);
        return htmlParser.parseBanks();
    }

    protected void onPostExecute(List<Bank> result) {
        super.onPostExecute(result);
        callback.load(result);
    }
}