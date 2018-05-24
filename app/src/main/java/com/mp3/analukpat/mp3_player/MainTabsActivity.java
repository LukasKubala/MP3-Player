package com.mp3.analukpat.mp3_player;

import android.app.TabActivity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

public class MainTabsActivity extends AppCompatActivity implements TabHost.TabContentFactory {
//Activity, die den Tabhost für Titel, Interpreten und Playlists aufruft
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);

        TabHost mainTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mainTabHost.setup();

        //mainTabHost.addTab(mainTabHost.newTabSpec("tab_Titel").setIndicator("Titel").setContent(new Intent(this, TabTitelActivity.class)));
       // mainTabHost.addTab(mainTabHost.newTabSpec("tab_Interpreten").setIndicator("Interpreten").setContent(new Intent(this, TabInterpretenActivity.class)));
       // mainTabHost.addTab(mainTabHost.newTabSpec("tab_Playlists").setIndicator("Playlists").setContent(new Intent(this, TabPlaylistsActivity.class)));
       // mainTabHost.setCurrentTab(0);
    }
    private TabHost.TabSpec kriegeTabSpec1(TabHost tabHost){
        return tabHost.newTabSpec("tab_Titel").setIndicator("Titel").setContent(this);
    }
    private TabHost.TabSpec kriegeTabSpec2(TabHost tabHost){
        return tabHost.newTabSpec("tab_Interpreten").setIndicator("Interpreten").setContent(this);
    }
    private TabHost.TabSpec kriegeTabSpec3(TabHost tabHost){
        return tabHost.newTabSpec("tab_Playlists").setIndicator("Playlists").setContent(this);
    }
    @Override
    public View createTabContent(String tag) {
        return LayoutInflater.from(this).inflate(R.layout.activity_tab_titel, null);
    }
}
