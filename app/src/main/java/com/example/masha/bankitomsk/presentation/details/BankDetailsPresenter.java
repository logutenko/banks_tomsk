package com.example.masha.bankitomsk.presentation.details;

import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.domain.BankDetailsModel;


/**
 * Created by masha on 03.04.2018.
 */

public class BankDetailsPresenter {
    private BankDetailsModel model;
    private BankDetailsActivity view;

    public BankDetailsPresenter (BankDetailsModel model){
        this.model = model;
    }

    public void attachView(BankDetailsActivity view){
        this.view = view;
    }

    public void detachView(){
        view = null;
    }

    public void showInfo(){
        model.getBankDetails(new BankDetailsModel.GetBankDetailsCallback() {
            @Override
            public void getDetails(Bank bank) {
                view.showDetails(bank);
            }
        });

    }
}
