package com.example.samsung.jsonapp;

/**
 * Created by Samsung on 12-10-2016.
 */
public class ListItems {

    private String title;
    private  String author;
    private String url;
    private  String subreddit;
    private String thumbnail;

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
