package com.thenextbiggeek.iphonexsxrwallpapers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class ListAdapter extends BaseAdapter {

    private Activity activity;
    private Context context;
    private int resId[];
    private static LayoutInflater mLayoutInflater = null;


    public ListAdapter(int x[], Activity a, Context c){
        activity = a;
        resId = x;
        context = c;
    }

    @Override
    public int getCount() {
        return resId.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            mLayoutInflater = LayoutInflater.from(context);
            vi = mLayoutInflater.inflate(R.layout.list_element, null);

        ImageView thumb_image=(ImageView)vi.findViewById(R.id.listBlockImage); // thumb image
        int x  = resId[position];
        Glide.with(context).load(x).into(thumb_image);
        //thumb_image.setImageResource(x);
        return vi;
    }
}
