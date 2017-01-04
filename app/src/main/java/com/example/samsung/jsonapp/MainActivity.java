package com.example.samsung.jsonapp;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MyRecyclelist";

    private List<ListItems> listItemsList=new ArrayList<ListItems>();
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;

    private int counter=0;
    private String count;
    private String jsonSubreddit;
    private String after_id;

    private static final String gaming="gaming";
    private static final String aww="aww";

    private  static final String funny="funny";
    private static final String food="food";
    private static final String subredditUrl="https://www.reddit.com/r/";
    private static final String jsonEnd="/.json";
    private static final String qCount="?count";
    private static final String after="&after";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);
     mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.BLACK).build());
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);


updateList(aww);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {

                int lastFirstVisiblePosition= ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                ((LinearLayoutManager)mRecyclerView.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);

                uploadMore(jsonSubreddit);
            }
        });

    }

    public void updateList(String subreddit){

        counter=5;

        subreddit=subredditUrl + subreddit + jsonEnd;

        adapter=new MyRecyclerAdapter(MainActivity.this,listItemsList);
        mRecyclerView.setAdapter(adapter);

        RequestQueue queue= Volley.newRequestQueue(this);

        adapter.clearAdapter();
        showPD();

        JsonObjectRequest jsObjRequest=new JsonObjectRequest(Request.Method.GET,subreddit,(String)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG,response.toString());
                hidePD();

                try{

                    JSONObject data=response.optJSONObject("data");
                    after_id=data.getString("after");
                    JSONArray children=data.getJSONArray("children");

                    for(int i=0;i<children.length();i++){

                        JSONObject post=children.getJSONObject(i).getJSONObject("data");

                        ListItems item=new ListItems();
                        item.setThumbnail(post.getString("thumbnail"));
                       item.setAuthor(post.getString("author"));
                        item.setUrl(post.getString("url"));
                        item.setTitle(post.getString("title"));

                        item.setSubreddit(post.getString("subreddit"));

                        jsonSubreddit=post.getString("subreddit");

                        listItemsList.add(item);

                    }

                }
                catch (JSONException e){

                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){


                VolleyLog.d(TAG,"error"+ error.getMessage());
                hidePD();

            }




        });


queue.add(jsObjRequest);

    }



    public void uploadMore(String subreddit){

        counter=counter+5;
        count=String.valueOf(counter);
        subreddit=jsonSubreddit;

        subreddit=subredditUrl + subreddit + jsonEnd + qCount + after + after_id;

        adapter=new MyRecyclerAdapter(MainActivity.this,listItemsList);
        mRecyclerView.setAdapter(adapter);

        RequestQueue queue= Volley.newRequestQueue(this);
        showPD();

        JsonObjectRequest jsObjRequest=new JsonObjectRequest(Request.Method.GET,subreddit,(String)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG,response.toString());
                hidePD();

                try{

                    JSONObject data=response.optJSONObject("data");
                    after_id=data.getString("after");
                    JSONArray children=data.getJSONArray("children");

                    for(int i=0;i<children.length();i++){

                        JSONObject post=children.getJSONObject(i).getJSONObject("data");

                        ListItems item=new ListItems();
                        item.setThumbnail(post.getString("thumbnail"));
                        item.setAuthor(post.getString("author"));
                        item.setUrl(post.getString("url"));
                        item.setTitle(post.getString("title"));

                        item.setSubreddit(post.getString("subreddit"));

                        jsonSubreddit=post.getString("subreddit");

                        listItemsList.add(item);

                    }

                }
                catch (JSONException e){

                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){


                VolleyLog.d(TAG,"error"+ error.getMessage());
                hidePD();

            }




        });


        queue.add(jsObjRequest);

    }


    private void showPD(){

        if(progressDialog==null) {

            progressDialog = new ProgressDialog(this);

            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

    }


    private void hidePD(){

        if(progressDialog!=null){

            progressDialog.dismiss();
            progressDialog=null;


        }


    }












}
