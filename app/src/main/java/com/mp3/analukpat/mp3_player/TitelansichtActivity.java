package com.mp3.analukpat.mp3_player;
// Activity, die die Tabs Titel, Interpreten und Playlists aufruft

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TitelansichtActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter_titelansichten;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager_titelansichten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titelansichten);

       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter_titelansichten = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager_titelansichten = findViewById(R.id.container);

        // dieser Aufruf crasht die App
        mViewPager_titelansichten.setAdapter(mSectionsPagerAdapter_titelansichten);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager_titelansichten.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager_titelansichten));

        //Button zum Menuwechsel holen
        Button menuwechsel_in_titelansicht = findViewById(R.id.menu_switch_in_titelansicht);

        //Instanziierung des Button zum Menuwechsel
        menuwechsel_in_titelansicht.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_titelansicht, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //deleted PlaceholderFragment class from here
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    Tab1_Titel tab1 = new Tab1_Titel();
                    return tab1;
                case 1:
                    Tab2_Interpreten tab2 = new Tab2_Interpreten();
                    return tab2;
                case 2:
                    Tab3_Playlists tab3 = new Tab3_Playlists();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle (int position){
            switch (position){
                case 0:
                    return "Titel";
                case 1:
                    return "Interpreten";
                case 2:
                    return "Playlists";
            }
            return null;
        }
    }
}
