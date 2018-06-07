package com.mp3.analukpat.mp3_player;
//Activity, die die Tabs Wiedergabe und Wiedergabeliste aufruft
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class WiedergabeActivity extends AppCompatActivity {
    //Buttons instanziieren
    private Button playbtn, pausebtn;
    private MediaPlayer mediaPlayer;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    //VolumeBar
    private SeekBar volume_bar;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiedergabe);

        //Initialisierung Buttons Play
        playbtn= findViewById(R.id.Play);
        pausebtn= findViewById(R.id.Pause);

        //Instanz mediaPlayer
        mediaPlayer=MediaPlayer.create(this, R.raw.lied1);

        //Listener
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WiedergabeActivity.this, "Es wird abgespielt", Toast.LENGTH_SHORT).show();
                buttonsState(false,true);
                mediaPlayer.start();
            }
        });

        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WiedergabeActivity.this, "Pause", Toast.LENGTH_SHORT).show();
                buttonsState(true, false);
                mediaPlayer.pause();
            }
        });

        //Volumebar
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //Wenn Wiedergabe zu Ende
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(WiedergabeActivity.this, "Fertig", Toast.LENGTH_SHORT).show();
                buttonsState(true, false);
            }
        });

    }
    //wechseln zw. Zustand der Buttons play und pause
    private void buttonsState(boolean playB, boolean pauseB) {
        playbtn.setEnabled(playB);
        pausebtn.setEnabled(pauseB);
    }

    //für Volumebar
    private void initControls() {
        try {
            volume_bar= findViewById(R.id.volume_bar);
            audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volume_bar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volume_bar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volume_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }catch (Exception e){

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wiedergabe, menu);
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
    //deleted Placeholderfragment class from here
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
            // Returning the current tabs
            switch (position){
                case 0:
                    Tab1_Einzel tab1 =new Tab1_Einzel();
                    return tab1;
                case 1:
                    Tab2_Wiedergabeliste tab2=new Tab2_Wiedergabeliste();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle (int position){
            switch (position){
                case 0:
                    return "Wiedergabe";
                case 1:
                    return "Wiedergabeliste";
            }
            return null;
        }
    }
}
