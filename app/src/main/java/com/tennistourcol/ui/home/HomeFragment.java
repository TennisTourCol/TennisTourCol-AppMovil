package com.tennistourcol.ui.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;

import com.tennistourcol.CustomAdapter;
import com.tennistourcol.R;
import com.tennistourcol.model.Match;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private ListView matchList;

    private static CustomAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        matchList = (ListView) root.findViewById(R.id.matchListView);
        ArrayList<Match> matchs = new ArrayList<>();
        matchs.add(Match.builder()
                .player1("Juan")
                .player2("David")
                .date(new Date())
                .hour("1:00 pm")
                .build());
        matchs.add(Match.builder()
                .player1("Manuel")
                .player2("Santiago")
                .date(new Date())
                .hour("12:00 m")
                .build());
        matchs.add(Match.builder()
                .player1("Daniel")
                .player2("Felipe")
                .date(new Date())
                .hour("5:00 pm")
                .build());
        matchs.add(Match.builder()
                .player1("Laura")
                .player2("Daniela")
                .date(new Date())
                .hour("6:00 pm")
                .build());
        matchs.add(Match.builder()
                .player1("Diana")
                .player2("Paula")
                .date(new Date())
                .hour("7:00 am")
                .build());
        matchs.add(Match.builder()
                .player1("Cesar")
                .player2("Pablo")
                .date(new Date())
                .hour("3:00 pm")
                .build());
        adapter= new CustomAdapter(matchs, root.getContext());
        matchList.setAdapter(adapter);
        matchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Match match= matchs.get(position);

                Snackbar.make(view, match.getPlayer1()/*+"\n"+match.getType()+" API: "+match.getVersion_number()*/, Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
        return root;
    }
}