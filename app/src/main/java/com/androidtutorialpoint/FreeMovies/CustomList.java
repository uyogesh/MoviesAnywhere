package com.androidtutorialpoint.FreeMovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;

    public CustomList(Activity context, String[] resource) {
        super(context, com.androidtutorialpoint.volleytutorial.R.layout.list_single,resource);
        this.context=context;
        this.web=resource;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(com.androidtutorialpoint.volleytutorial.R.layout.list_single,null,true);
        TextView txtTitle=(TextView)rowView.findViewById(com.androidtutorialpoint.volleytutorial.R.id.txt);
        txtTitle.setText(web[position]);

        return rowView;

    }
}
