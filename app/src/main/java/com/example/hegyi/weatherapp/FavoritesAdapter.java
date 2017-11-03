package com.example.hegyi.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FavoritesAdapter extends ArrayAdapter<String> {

    public FavoritesAdapter(@NonNull Context context, List<String> favoriteCities) {
        super(context, 0, favoriteCities);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String current = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.favorite_list_item, parent, false);
        }

        TextView cityName = (TextView) convertView.findViewById(R.id.favoriteCity);
        cityName.setText(current.toUpperCase());
        return convertView;
    }

}
