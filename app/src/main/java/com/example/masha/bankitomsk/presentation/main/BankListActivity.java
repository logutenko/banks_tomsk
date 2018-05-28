package com.example.masha.bankitomsk.presentation.main;

/**
 * Created by masha on 28.03.2018.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.masha.bankitomsk.R;
import com.example.masha.bankitomsk.data.Bank;
import com.example.masha.bankitomsk.domain.BanksModel;
import com.example.masha.bankitomsk.presentation.details.BankDetailsActivity;
import com.example.masha.bankitomsk.presentation.main.RecyclerView.BankListAdapter;
import com.example.masha.bankitomsk.presentation.common.ItemDecoration;
import com.example.masha.bankitomsk.presentation.main.RecyclerView.RecyclerItemClickListener;


import java.util.List;

/**
 * Created by masha on 20.03.2018.
 */

public class BankListActivity extends AppCompatActivity implements BanksPresenter.ShowBankList {

    private RecyclerView rvBanksList;

    private BankListAdapter adapter;
    private BanksPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks_list);

        adapter = new BankListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBanksList = findViewById(R.id.rvBanksList);
        rvBanksList.setLayoutManager(layoutManager);
        rvBanksList.setAdapter(adapter);
        rvBanksList.addItemDecoration(new ItemDecoration(this, R.drawable.divider));
        rvBanksList.addOnItemTouchListener(new RecyclerItemClickListener(this, rvBanksList, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(BankListActivity.this, BankDetailsActivity.class);
                        intent.putExtra("URL", adapter.getLink(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        getSupportActionBar().setTitle("Курсы валют Томских банков");
        BanksModel model = new BanksModel();
        presenter = new BanksPresenter(model);
        presenter.attachView(this);
        presenter.loadBanks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public void showBanks(List<Bank> banks) {
        adapter.setData(banks);
    }
}
