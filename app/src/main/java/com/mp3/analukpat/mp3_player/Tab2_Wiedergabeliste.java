package com.mp3.analukpat.mp3_player;
// Die Wiedergabeliste, zu der aus man zur Wiedergabe eines einzelnen Liedes gelangt
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Tab2_Wiedergabeliste extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2wiedergabeliste, container, false);




        return rootView;
    }
}
