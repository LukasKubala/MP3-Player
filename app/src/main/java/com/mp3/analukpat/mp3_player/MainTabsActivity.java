package com.mp3.analukpat.mp3_player;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainTabsActivity extends AppCompatActivity {
//Activity, die den Tabhost für Titel, Interpreten und Playlists aufruft
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);
    }
}
