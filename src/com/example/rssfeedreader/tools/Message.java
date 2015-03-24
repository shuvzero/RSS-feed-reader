package com.example.rssfeedreader.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Message {

    private Context context;

    public Message(Context context) {
        this.context = context;
    }

    public void show(String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss dialog
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
