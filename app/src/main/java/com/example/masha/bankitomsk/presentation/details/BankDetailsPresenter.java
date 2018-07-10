package com.example.masha.bankitomsk.presentation.details;

import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.domain.BankDetailsModel;


/**
 * Created by masha on 03.04.2018.
 */

public class BankDetailsPresenter {
    private BankDetailsModel model;
    private IBankDetailsView view;
    private String detailsUrl;

    public BankDetailsPresenter(String url) {
        detailsUrl = url;
        model = new BankDetailsModel();
    }

    public void attachView(IBankDetailsView view) {
        this.view = view;
        showInfo();
    }

    public void detachView() {
        view = null;
    }

    public void showInfo() {
        model.getBankDetails(detailsUrl, new BankDetailsModel.BankDetailsCallback() {
            @Override
            public void getDetails(Bank bank) {
                if (bank == null) view.noInfo();
                else view.showDetails(bank);
            }
        });

    }

}
