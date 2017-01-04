package com.example.samsung.jsonapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by Samsung on 12-10-2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<ListRowViewHolder> {

    private List<ListItems> listItemsList;
    private Context mContext;
    private ImageLoader mImageLoader;
    private int focusedItem=0;

    public MyRecyclerAdapter(Context context,List<ListItems> listItemsList){

        this.mContext=context;
        this.listItemsList=listItemsList;
    }


    @Override
    public ListRowViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row,null);

        ListRowViewHolder holder=new ListRowViewHolder(v);
        /*

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // TextView redditUrl= (TextView) v.findViewById(R.id.url);

                //String postUrl=redditUrl.getText().toString();

             //   Intent intent=new Intent(mContext,WebActivity.class);

               // intent.putExtra("url",postUrl);
                //mContext.startActivity(intent);
            }
        });
*/
        return holder;
    }

    @Override
    public void onBindViewHolder(ListRowViewHolder holder, int position) {

        ListItems listItems=listItemsList.get(position);

        holder.itemView.setSelected(focusedItem==position);

        holder.getLayoutPosition();
        mImageLoader=MySingleton.getInstance(mContext).getImageLoader();


        holder.thumbnail.setImageUrl(listItems.getThumbnail(),mImageLoader);
        holder.thumbnail.setDefaultImageResId(R.drawable.reddit_placeholder);

        holder.title.setText(Html.fromHtml(listItems.getTitle()));
        holder.author.setText(Html.fromHtml(listItems.getAuthor()));
        holder.subreddit.setText(Html.fromHtml(listItems.getSubreddit()));
        holder.url.setText(Html.fromHtml(listItems.getUrl()));



    }

    public void clearAdapter(){


        listItemsList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return(null!=listItemsList?listItemsList.size():0);
    }
}
