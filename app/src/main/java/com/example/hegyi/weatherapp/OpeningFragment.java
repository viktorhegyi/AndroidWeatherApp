package com.example.hegyi.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class OpeningFragment extends Fragment {

    EditText cityChooser;
    Button favorite;

    public OpeningFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_opening, container, false);
        cityChooser = (EditText) rootView.findViewById(R.id.choose_city);

        cityChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCity(cityChooser.getText().toString());
            }
        });

        favorite = (Button) rootView.findViewById(R.id.favorite_btn);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteFragment ff = new FavoriteFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, ff);
                transaction.commit();
            }
        });

        return rootView;
    }

    public void changeCity(String city){
        WeatherFragment wf = new WeatherFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("city", city);
        wf.setArguments(bundle);
        transaction.replace(R.id.container, wf);
        transaction.addToBackStack(null);
        transaction.commit();
        wf.changeCity(city);
    }


}
