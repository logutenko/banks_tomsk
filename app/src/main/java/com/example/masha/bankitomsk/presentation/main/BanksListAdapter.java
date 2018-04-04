package com.example.masha.bankitomsk.presentation.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.masha.bankitomsk.R;
import com.example.masha.bankitomsk.data.Bank;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by masha on 22.03.2018.
 */

public class BanksListAdapter extends RecyclerView.Adapter<BanksListAdapter.ViewHolder> {

    List<Bank> banks = new LinkedList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(banks.get(position));
    }

    @Override
    public int getItemCount(){

        return banks.size();
    }

    public void setData(List<Bank> banksUpdate) {

        banks.clear();
        banks.addAll(banksUpdate);
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvBuyCurrency1, tvSellCurrency1,
                tvBuyCurrency2, tvSellCurrency2;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvBuyCurrency1 = itemView.findViewById(R.id.tvBuyCurrency1);
            tvSellCurrency1 = itemView.findViewById(R.id.tvSellCurrency1);
            tvBuyCurrency2 = itemView.findViewById(R.id.tvBuyCurrency2);
            tvSellCurrency2 = itemView.findViewById(R.id.tvSellCurrency2);

        }

        void bind(Bank bank) {
            tvName.setText(bank.getName());
            tvBuyCurrency1.setText(bank.getRateToBuy().get("USD"));
            tvSellCurrency1.setText(bank.getRateToSell().get("USD"));
            //tvBuyCurrency2.setText(bank.getRateToBuy().get("EUR"));
            //tvSellCurrency2.setText(bank.getRateToSell().get("EUR"));
        }
    }

}
