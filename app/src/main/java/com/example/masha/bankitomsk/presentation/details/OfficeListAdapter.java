package com.example.masha.bankitomsk.presentation.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.masha.bankitomsk.R;
import com.example.masha.bankitomsk.data.Office;

import java.util.ArrayList;

/**
 * Created by masha on 05.05.2018.
 */

public class OfficeListAdapter extends ArrayAdapter<Office> {

    private int listItemLayout;

    public OfficeListAdapter(Context context, int resource, ArrayList<Office> itemList) {
        super(context, resource, itemList);
        listItemLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Office item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.address = convertView.findViewById(R.id.tvAddress);
            viewHolder.phone = convertView.findViewById(R.id.tvPhone);
            convertView.setTag(viewHolder); // view lookup cache stored in tag
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.address.setText(item.getAddress());
        viewHolder.phone.setText(item.getPhone());

        // Return the completed view to render on screen
        return convertView;
    }

    public void setData(ArrayList<Office> banksUpdate) {

        clear();
        addAll(banksUpdate);
        notifyDataSetChanged();

    }



    private static class ViewHolder {
        TextView address;
        TextView phone;
    }
}
