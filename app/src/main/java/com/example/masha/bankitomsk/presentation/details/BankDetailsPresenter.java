package com.example.masha.bankitomsk.presentation.details;

import com.example.masha.bankitomsk.domain.BanksModel;

/**
 * Created by masha on 03.04.2018.
 */

public class BankDetailsPresenter {
    private BanksModel model;
    private BankInfoActivity view;

    public BankDetailsPresenter (BanksModel model){
        this.model = model;
    }

    public void attachView(BankInfoActivity view){
        this.view = view;
    }

    public void detachView(){
        view = null;
    }

    public void showInfo(){

    }
}
