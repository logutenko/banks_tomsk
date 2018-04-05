package com.example.masha.bankitomsk.presentation.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.masha.bankitomsk.R;
import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.data.Office;
import com.example.masha.bankitomsk.domain.BankDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class BankDetailsActivity extends AppCompatActivity {

    private BankDetailsPresenter presenter;
    private TextView tvFullName, tvWebSite,tvBuy, tvSell;
    private ListView lvOffices;
    private ArrayList<Office> listItems;
    private OfficeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);

        //init
        tvFullName = findViewById(R.id.tvFullName);
        tvWebSite = findViewById(R.id.tvWebSite);
        tvBuy = findViewById(R.id.tvBuy);
        tvSell = findViewById(R.id.tvSell);
        lvOffices = findViewById(R.id.lvOffices);
        listItems = new ArrayList<Office>();
        listItems.add(new Office("test", "123"));
        adapter = new OfficeListAdapter(this, R.layout.office_item, listItems);
        lvOffices.setAdapter(adapter);
        String url = getIntent().getStringExtra("URL");
        tvFullName.setText(url);
        BankDetailsModel model = new BankDetailsModel(url);
        presenter = new BankDetailsPresenter(model);
        presenter.attachView(this);
        presenter.showInfo();

    }

    public void showDetails(Bank bank){
        tvFullName.setText(bank.getName());
        tvWebSite.setText(bank.getWebSite());
        listItems = (ArrayList)bank.getOffices();
        adapter.setData(listItems);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
