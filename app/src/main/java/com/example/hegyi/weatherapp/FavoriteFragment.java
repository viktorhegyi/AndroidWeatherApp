package com.example.hegyi.weatherapp;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    Button back;
    SharedPreferences sharedPreferences;

    public FavoriteFragment() {
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        back = (Button) rootView.findViewById(R.id.favoriteBack_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningFragment of = new OpeningFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, of);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        sharedPreferences = getActivity().getSharedPreferences("PREFS", 0);
        String cities = sharedPreferences.getString("words", "");
        String[] itemsWords = cities.split(",");
        List<String> favoriteCities = new ArrayList<>();
        for (int i = 0; i < itemsWords.length ; i++) {
            favoriteCities.add(itemsWords[i]);
        }
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(getContext(), favoriteCities);
        final ListView listView = (ListView)rootView.findViewById(R.id.favoritesListView);
        listView.setAdapter(favoritesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeCity(listView.getItemAtPosition(position).toString());
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
