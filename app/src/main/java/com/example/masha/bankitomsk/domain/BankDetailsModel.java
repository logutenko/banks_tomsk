package com.example.masha.bankitomsk.domain;

import android.os.AsyncTask;

import com.example.masha.bankitomsk.data.Bank;

/**
 * Created by masha on 04.04.2018.
 */

public class BankDetailsModel {
    private final HTMLParser htmlParser;
    private String url;


    public BankDetailsModel(String url){

        this.url = url;
        htmlParser = new HTMLParser();
    }

    public void getBankDetails(GetBankDetailsCallback callback){

        GetBankDetailsTask getBankDetailsTask = new GetBankDetailsTask(callback);
        getBankDetailsTask.execute();
    }

    public interface GetBankDetailsCallback{
        public void getDetails(Bank bank);
    }

    class GetBankDetailsTask extends AsyncTask<Void, Void, Bank> {

        private final GetBankDetailsCallback callback;

        GetBankDetailsTask(GetBankDetailsCallback callback){
            this.callback = callback;
        }
        @Override
        protected Bank doInBackground(Void... params) {

            htmlParser.setURL(url);
            return htmlParser.parseBankDetails();

        }

        protected void onPostExecute(Bank result) {
            super.onPostExecute(result);
            callback.getDetails(result);
        }
    }
}
