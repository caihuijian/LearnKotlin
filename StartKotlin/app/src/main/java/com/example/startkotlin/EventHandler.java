package com.example.startkotlin;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class EventHandler {
    private Context mContext;

    public EventHandler(Context context) {
        mContext = context;
    }

    public void onClickFriend(View view) {
        Toast.makeText(mContext, "onClickFriend", Toast.LENGTH_LONG).show();
    }
}
