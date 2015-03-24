package com.example.rssfeedreader.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.rssfeedreader.R;

public class DetailsActivity extends Activity{

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Intent intent = getIntent();
        activity = this;

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(intent.getStringExtra("title"));
        TextView pubDate = (TextView) findViewById(R.id.pubDate);
        pubDate.setText(intent.getStringExtra("pubDate"));
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(intent.getStringExtra("description"));

        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openMainActivity = new Intent(activity, MainActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(openMainActivity);
            }
        });


    }
}
