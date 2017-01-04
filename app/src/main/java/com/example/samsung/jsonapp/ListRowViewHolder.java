package com.example.samsung.jsonapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.net.NetworkInterface;

/**
 * Created by Samsung on 12-10-2016.
 */
public class ListRowViewHolder extends RecyclerView.ViewHolder {

    protected NetworkImageView thumbnail;


    protected TextView title;

    protected TextView subreddit;
    protected TextView url;
    protected TextView author;
    protected RelativeLayout relativeLayout;


    public ListRowViewHolder(View view) {
        super(view);


this.thumbnail=(NetworkImageView)view.findViewById(R.id.network_image);
        this.title= (TextView) view.findViewById(R.id.title);
        this.author= (TextView) view.findViewById(R.id.author);
        this.subreddit= (TextView) view.findViewById(R.id.sureddit);
        this.url= (TextView) view.findViewById(R.id.url);
        this.relativeLayout= (RelativeLayout) view.findViewById(R.id.relLayout);

        //view.setClickable(true);



    }
}