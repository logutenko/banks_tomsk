package com.example.masha.bankitomsk.domain;

import android.os.AsyncTask;

import com.example.masha.bankitomsk.data.Bank;

/**
 * Created by masha on 15.05.2018.
 */

class BankDetailsTask extends AsyncTask<Void, Void, Bank> {

    private final BankDetailsModel.BankDetailsCallback callback;
    private String url;

    BankDetailsTask(String url, BankDetailsModel.BankDetailsCallback callback) {
        this.callback = callback;
        this.url = url;
    }

    @Override
    protected Bank doInBackground(Void... params) {

        HTMLParser htmlParser = new HTMLParser(url);
        return htmlParser.parseBankDetails();

    }

    protected void onPostExecute(Bank result) {
        super.onPostExecute(result);
        callback.getDetails(result);
    }
}