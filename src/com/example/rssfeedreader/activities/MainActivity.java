package com.example.rssfeedreader.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.rssfeedreader.R;
import com.example.rssfeedreader.rss.RssDataLoadTask;

public class MainActivity extends Activity {

    private MainActivity local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        local = this;

        Button loadButton = (Button) findViewById(R.id.load);
        final EditText url = (EditText) findViewById(R.id.url);
        final EditText filter = (EditText) findViewById(R.id.filter);

        loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListView rssItems = (ListView) findViewById(R.id.rssItems);
                RssDataLoadTask task = new RssDataLoadTask(local, rssItems);
                task.execute(url.getText().toString(), filter.getText().toString());
            }
        });
    }

}