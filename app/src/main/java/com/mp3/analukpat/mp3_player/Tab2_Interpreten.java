package com.mp3.analukpat.mp3_player;
//Anzeige aller Interpreten und der zugehörigen Titel

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab2_Interpreten extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2_interpreten, container, false);
        return rootView;
    }
}
