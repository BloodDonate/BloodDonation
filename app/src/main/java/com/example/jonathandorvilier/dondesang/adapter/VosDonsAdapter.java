package com.example.jonathandorvilier.dondesang.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jonathandorvilier.dondesang.R;
import com.example.jonathandorvilier.dondesang.model.VosDons;

import java.util.List;

/**
 * Created by ingdjason on 9/11/17.
 */


public class VosDonsAdapter extends ArrayAdapter<VosDons> {

    TextView tvNomUser,tvDateCheck;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    public VosDonsAdapter(Context context, List<VosDons> vosDons){
        super(context, android.R.layout.simple_list_item_1, vosDons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final VosDons VosDons = this.getItem(position);

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_vos_dons, parent, false);
        }

        sharedPreferences = getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        tvNomUser=(TextView) convertView.findViewById(R.id.tvNom);
        tvDateCheck=(TextView) convertView.findViewById(R.id.tvDateCheck);
        tvNomUser.setText(VosDons.getDonner_a());
        tvDateCheck.setText(VosDons.getDate_check_don());

        return convertView;
    }
}