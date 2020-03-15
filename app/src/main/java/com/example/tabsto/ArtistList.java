package com.example.tabsto;

import android.app.Activity;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<XYValue> {
    private Activity context;
    private List<XYValue> artistList;
    public ArtistList(Activity context,List<XYValue> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context=context;
        this.artistList = artistList;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem =inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewName=(TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        XYValue artist=artistList.get(position);
        textViewName.setText(artist.getName());
        textViewGenre.setText(artist.getDate());
        return listViewItem;
    }
}
