package com.mp3.analukpat.mp3_player;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WiedergabeTabsActivity extends AppCompatActivity {
    //Activity, die den Tabhost für die Wiedergabe und die Wiedergabeliste aufruft
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiedergabe_tabs);
    }
}
