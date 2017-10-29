package com.example.aleksei.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aleksei on 29/10/17.
 */

public class DrinkArrayAdapter extends ArrayAdapter<Pair<Drink, Integer>> {

    private static class ViewHolder {
        TextView countView;
        TextView nameView;
        ImageView imageView;
    }

    public DrinkArrayAdapter(@NonNull Context context, int resource, @NonNull List<Pair<Drink, Integer>> objects) {
        super(context, resource, objects);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.drink_list_item, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.countView = convertView.findViewById(R.id.count);
            viewHolder.nameView = convertView.findViewById(R.id.name);
            viewHolder.imageView = convertView.findViewById(R.id.pic);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Pair<Drink, Integer> item = getItem(position);
        if (item != null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.countView.setText(item.second+" x");
            viewHolder.nameView.setText(item.first.getType());
            viewHolder.imageView.setImageResource(item.first.getImage());
        }

        return convertView;
    }
}
