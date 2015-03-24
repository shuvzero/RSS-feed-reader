package com.example.rssfeedreader.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rssfeedreader.R;
import com.example.rssfeedreader.rss.RssItem;
import com.example.rssfeedreader.tools.ImageLoader;

import java.util.List;

public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private List<RssItem> data;
    private LayoutInflater inflater;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity activity, List<RssItem> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(this.activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null)
            view = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)view.findViewById(R.id.title);
        TextView description = (TextView)view.findViewById(R.id.description);
        TextView pubDate = (TextView)view.findViewById(R.id.pubDate);
        ImageView thumb_image = (ImageView)view.findViewById(R.id.list_image);

        RssItem rssItem = data.get(position);

        title.setText(rssItem.getTitle());
        description.setText(rssItem.getShortDescription());
        pubDate.setText(rssItem.getPubDate());
        //thumb_image.setImageBitmap(rssItem.getThumbnail());
        imageLoader.DisplayImage(rssItem.getImagePath(), thumb_image);
        return view;
    }
}
