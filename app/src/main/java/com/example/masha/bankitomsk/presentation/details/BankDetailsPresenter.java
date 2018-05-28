package com.example.masha.bankitomsk.presentation.details;

import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.domain.BankDetailsModel;


/**
 * Created by masha on 03.04.2018.
 */

public class BankDetailsPresenter {
    private BankDetailsModel model;
    private BankDetailsActivity view;
    private String detailsUrl;

    public BankDetailsPresenter (){
        model = new BankDetailsModel();
    }

    public void attachView(BankDetailsActivity view){
        this.view = view;
        showInfo();
    }

    public void detachView(){
        view = null;
    }

    public void showInfo(){
        model.getBankDetails(detailsUrl, new BankDetailsModel.BankDetailsCallback() {
            @Override
            public void getDetails(Bank bank) {
                view.showDetails(bank);
            }
        });

    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
}
