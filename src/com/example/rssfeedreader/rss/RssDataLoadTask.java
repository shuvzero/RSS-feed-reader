package com.example.rssfeedreader.rss;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import com.example.rssfeedreader.list.LazyAdapter;
import com.example.rssfeedreader.list.ListListener;
import com.example.rssfeedreader.tools.Message;

import java.util.List;

public class RssDataLoadTask extends AsyncTask<String, Void, List<RssItem> > {

    private Activity activity;
    private ListView rssItems;

    public RssDataLoadTask(Activity activity, ListView rssItems) {
        this.activity = activity;
        this.rssItems = rssItems;
    }

    @Override
    protected List<RssItem> doInBackground(String... params) {
        try {
            RssReader rssReader = new RssReader(params[0], params[1]);
            return rssReader.getItems();
        } catch (Exception e) {
            Log.e("RssDataTask", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<RssItem> result) {
        if(result == null) {
            Message message = new Message(activity);
            message.show("Error", "Invalid url or xml is not well-formed");
            return;
        }
        //ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(activity,android.R.layout.simple_list_item_1, result);
        //rssItems.setAdapter(adapter);

        LazyAdapter adapter=new LazyAdapter(activity, result);
        rssItems.setAdapter(adapter);
        rssItems.setOnItemClickListener(new ListListener(result, activity));
    }
}
