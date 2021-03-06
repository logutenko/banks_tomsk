package com.example.masha.bankitomsk.presentation.main;

import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.domain.BanksModel;

import java.util.List;

/**
 * Created by masha on 20.03.2018.
 */

public class BanksPresenter {

    private BanksModel model;
    private IBanksView view;

    public BanksPresenter(BanksModel model) {
        this.model = model;
    }

    public void attachView(IBanksView view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public void loadBanks() {
        model.loadBanks(new BanksModel.LoadBanksCallback() {
            @Override
            public void load(List<Bank> banks) {
                view.showBanks(banks);
            }
        });
    }

}
