package com.example.rssfeedreader.rss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RssItem {

    public static final int THUMBNAIL_SIZE = 48;

    private String title;
    private String description;
    private Date pubDate;
    private String imagePath;
    private Bitmap bitmap;

    private static SimpleDateFormat requiredDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static SimpleDateFormat sourceDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        final int MAX_DESCRIPTION_LENGTH = 100;
        if(description.length() < MAX_DESCRIPTION_LENGTH)
            return description;
        else return description.substring(0, MAX_DESCRIPTION_LENGTH) + "...";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return requiredDateFormat.format(pubDate);
    }

    public void setPubDate(String pubDate) {
        try {
            this.pubDate = sourceDateFormat.parse(pubDate);
        } catch (ParseException e) {
            Log.e("rssItem", "date parsing");
        }
    }

    @Override
    public String toString() {
        return title + "\n" + getPubDate() + "\n" + getShortDescription()
                + "\n" + getImagePath();
    }

    public Bitmap getThumbnail() {
        new RetrieveImageTask(this).execute(imagePath);
        return bitmap;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    class RetrieveImageTask extends AsyncTask<String, Void, Bitmap> {

        private Exception exception;
        private RssItem rssItem;

        public RetrieveImageTask(RssItem rssItem) {
            this.rssItem = rssItem;
        }

        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                Bitmap source = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                Bitmap thumbnail = ThumbnailUtils.extractThumbnail(source, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
                rssItem.bitmap = thumbnail;
                return thumbnail;
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute() {
            //rssItem.bitmap =
        }
    }
}
