package com.example.startkotlin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.startkotlin.databinding.ItemTitleBinding;
import com.example.startkotlin.databinding.ItemUserLayoutBinding;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getCount() {
        //need add the count of title
        return userList.size() + 1;
    }

    @Override
    public User getItem(int position) {
        //need exclude index 0(it is position for title)
        if (position > 0) {
            //position 0 is title,so position from need - 1,that's the position for users
            return userList.get(position - 1);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        //there two type of view
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            //if position is 0,it is type for title
            return 0;
        } else {
            //if not,it is type for user
            return 1;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemUserLayoutBinding itemUserLayoutBinding = null;
        ItemTitleBinding itemTitleBinding = null;
        //需要区分user和title的显示，其实按照如下格式，代码可读性更强
        //if (convertView == null) {
        //    switch (type) {
        //        case 0:
        //            ...
        //            //all code for title
        //        case 1:
        //            ...
        //            //all code for user
        //        default:
        //            return null;

        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case 0:
                    itemTitleBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_title, parent, false);
                    convertView = itemTitleBinding.getRoot();
                    break;
                case 1:
                    itemUserLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_user_layout, parent, false);
                    convertView = itemUserLayoutBinding.getRoot();
                    break;
            }
        } else {

            switch (type) {
                case 0:
                    itemTitleBinding = DataBindingUtil.getBinding(convertView);
                    break;
                case 1:
                    itemUserLayoutBinding = DataBindingUtil.getBinding(convertView);
                    break;
            }
        }

        switch (type) {
            case 0:
                itemTitleBinding.setHandler(new EventHandler(context));
                return itemTitleBinding.getRoot();
            case 1:
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Click " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                itemUserLayoutBinding.setUseritem(userList.get(position - 1));
                return itemUserLayoutBinding.getRoot();
            default:
                return null;
        }

    }
}
