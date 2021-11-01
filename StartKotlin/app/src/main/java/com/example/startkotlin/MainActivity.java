package com.example.startkotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.startkotlin.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> mUserList;
    private UserAdapter mUserAdapter;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
    }

    private void initData() {
        mUserList = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            User u = new User("Caihuijian", 27);
            mUserList.add(u);
        }
        mUserAdapter = new UserAdapter(this, mUserList);
        mainBinding.listAll.setAdapter(mUserAdapter);
    }
}


