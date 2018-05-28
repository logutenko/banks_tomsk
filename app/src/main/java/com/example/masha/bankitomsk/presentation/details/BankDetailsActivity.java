package com.example.masha.bankitomsk.presentation.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.masha.bankitomsk.R;
import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.data.Currency;
import com.example.masha.bankitomsk.presentation.common.ItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class BankDetailsActivity extends AppCompatActivity implements IBankDetailsView {

    private BankDetailsPresenter presenter;
    private TextView tvWebSite, tvBuy, tvSell;
    private RecyclerView rvOffices;
    private OfficeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //init
        tvWebSite = findViewById(R.id.tvWebSite);
        tvBuy = findViewById(R.id.tvBuy);
        tvSell = findViewById(R.id.tvSell);
        rvOffices = findViewById(R.id.rvOffices);
        adapter = new OfficeListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOffices.setLayoutManager(layoutManager);
        rvOffices.setAdapter(adapter);
        rvOffices.addItemDecoration(new ItemDecoration(this, R.drawable.divider));
        String url = getIntent().getStringExtra(getString(R.string.key_URL));
        presenter = new BankDetailsPresenter(url);
        presenter.attachView(this);


    }

    public void showDetails(Bank bank) {

        getSupportActionBar().setTitle(bank.getName());
        tvWebSite.setClickable(true);
        tvWebSite.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://" + bank.getWebSite() + "'>" + bank.getWebSite() + "</a>";

        tvWebSite.setText(Html.fromHtml(text));
        List<Currency> currencyList = bank.getCurrencies();
        for (Currency currency : currencyList) {
            if (currency.getName().equals(getString(R.string.usd))) {
                tvBuy.setText(currency.getRateToBuy());
                tvSell.setText(currency.getRateToSell());
            }
        }
        adapter.setData((ArrayList) bank.getOffices());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
