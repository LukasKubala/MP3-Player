package com.mp3.analukpat.mp3_player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
//diese Klasse dient zur Anzeige von Informationen in der ListView
public class AnzeigeAdapter extends BaseAdapter {
    private ArrayList <Lied> lieder;
    private LayoutInflater liedinf;

    public AnzeigeAdapter(ArrayList <Lied> meineLieder, Context k){
        lieder=meineLieder;
        liedinf=LayoutInflater.from(k);
    }

    @Override
    public int getCount() {
        return lieder.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
// die folgende Methode zeigt alle Lieder in der Listview an. Wird in Tab1_Titel aufgerufen
    // hier werden nur Titel angezeigt
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout lay = (RelativeLayout) liedinf.inflate(R.layout.fragment_tab1_titel, parent,false);
        ListView ganzeView = (ListView) lay.findViewById(R.id.listViewTitel);


        TextView titelV = (TextView) lay.findViewById(R.id.section_interpret);
        TextView interpretV=(TextView)lay.findViewById(R.id.section_interpret);



        Lied akt = lieder.get(position);


        titelV.setText(akt.getTitel());
        //interpretV.setText(akt.getInterpret());

        lay.setTag(position);

        return lay;
    }
}
