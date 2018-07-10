package com.example.masha.bankitomsk.presentation.details;

import com.example.masha.bankitomsk.data.Bank;

/**
 * Created by masha on 28.05.2018.
 */

public interface IBankDetailsView {
    void showDetails(Bank bank);
    void noInfo();
}
