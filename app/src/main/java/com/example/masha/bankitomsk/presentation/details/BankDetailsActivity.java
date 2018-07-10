package com.example.masha.bankitomsk.presentation.details;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    private LinearLayout lLoading;
    private ConstraintLayout clNoInfo;
    private ScrollView svBankDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_bank_details);
        lLoading = findViewById(R.id.lLoading);
        String url = getIntent().getStringExtra(getString(R.string.key_URL));
        presenter = new BankDetailsPresenter(url);
        presenter.attachView(this);


    }

    public void showDetails(Bank bank) {

        initDetailsView();
        getSupportActionBar().setTitle(bank.getName());
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        svBankDetails.setVisibility(View.VISIBLE);
        lLoading.setVisibility(View.GONE);
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

    private void initDetailsView() {

        svBankDetails = findViewById(R.id.svBankDetails);
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
    }

    public void noInfo() {

        clNoInfo = findViewById(R.id.clNoInfo);
        lLoading.setVisibility(View.GONE);
        clNoInfo.setVisibility(View.VISIBLE);
        getSupportActionBar().setTitle(R.string.unknown);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
