package com.example.masha.bankitomsk.presentation.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.masha.bankitomsk.R;
import com.example.masha.bankitomsk.data.Office;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by masha on 05.05.2018.
 */

public class OfficeListAdapter extends RecyclerView.Adapter<OfficeListAdapter.ViewHolder> {


    List<Office> offices = new LinkedList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.office_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(offices.get(position));
    }

    @Override
    public int getItemCount() {
        return offices.size();
    }

    public void setData(ArrayList<Office> officesUpdate) {

        offices.clear();
        offices.addAll(officesUpdate);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAddress, tvPhone;


        public ViewHolder(View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhone = itemView.findViewById(R.id.tvPhone);

        }

        void bind(Office office) {
            tvAddress.setText(office.getAddress());
            tvPhone.setText(office.getPhone());
        }
    }

}
