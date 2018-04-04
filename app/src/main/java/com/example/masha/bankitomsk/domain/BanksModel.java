package com.example.masha.bankitomsk.domain;

import android.os.AsyncTask;
import android.util.Log;

import com.example.masha.bankitomsk.data.Bank;

import java.util.List;

/**
 * Created by masha on 20.03.2018.
 */

public class BanksModel {

    private final HTMLParser htmlParser;


    public BanksModel(){

        htmlParser = new HTMLParser();
    }

    public void loadBanks(LoadBanksCallback callback){

        LoadBankTask loadBankTask = new LoadBankTask(callback);
        loadBankTask.execute();
    }

    public interface LoadBanksCallback{
       public void load(List<Bank> banks);
    }

    class LoadBankTask extends AsyncTask<Void, Void, List<Bank>> {

        private final LoadBanksCallback callback;

        LoadBankTask(LoadBanksCallback callback){
            this.callback = callback;
        }
        @Override
        protected List<Bank> doInBackground(Void... params) {
            Log.d("Test", "doInBackground");

            htmlParser.setURL("http://banki.tomsk.ru/pages/41/");
            List<Bank> banksList = htmlParser.parseBanks();
            return banksList;
        }

        protected void onPostExecute(List<Bank> result) {
            super.onPostExecute(result);
            callback.load(result);
        }
    }
}
