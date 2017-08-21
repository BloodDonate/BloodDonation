package com.example.jonathandorvilier.dondesang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathandorvilier.dondesang.R;

/**
 * Created by Jonathan Dorvilier on 8/18/2017.
 */

public class CustomSpinnerAdapter extends BaseAdapter{

    Context context;
    int flags[];
    String[] BloodGroup;
    LayoutInflater inflter;

    public CustomSpinnerAdapter(Context applicationContext, int[] flags, String[] BloodGroup) {
        this.context = applicationContext;
        this.flags = flags;
        this.BloodGroup = BloodGroup;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.ivImage);
        TextView names = (TextView) view.findViewById(R.id.tvCustomSpinner);
        icon.setImageResource(flags[i]);
        names.setText(BloodGroup[i]);
        return view;
    }
}
