package com.example.rssfeedreader.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.example.rssfeedreader.activities.DetailsActivity;
import com.example.rssfeedreader.rss.RssItem;

import java.util.List;

public class ListListener implements OnItemClickListener {

    List<RssItem> listItems;
    Activity activity;

    public ListListener(List<RssItem> listItems, Activity activity) {
        this.listItems = listItems;
        this.activity  = activity;
    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        RssItem item = listItems.get(pos);
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra("title", item.getTitle());
        intent.putExtra("pubDate", item.getPubDate().toString());
        intent.putExtra("description", item.getDescription());
        activity.startActivity(intent);

    }

}