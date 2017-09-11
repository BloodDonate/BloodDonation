package com.example.jonathandorvilier.dondesang.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jonathandorvilier.dondesang.R;
import com.example.jonathandorvilier.dondesang.model.DemandeSang;

import java.util.List;

/**
 * Created by ingdjason on 9/11/17.
 */

public class VosDemandesAdapter extends ArrayAdapter<DemandeSang> {

    TextView tvHopital,tvQteDemand,tvTypeDemand,tvExpDemande;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    public VosDemandesAdapter(Context context, List<DemandeSang> DemandeSang){
        super(context, android.R.layout.simple_list_item_1, DemandeSang);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DemandeSang DemandeSang = this.getItem(position);

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_vos_demande, parent, false);
        }

        sharedPreferences = getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        tvHopital=(TextView) convertView.findViewById(R.id.tvHopital);
        tvQteDemand=(TextView) convertView.findViewById(R.id.tvQteDemand);
        tvTypeDemand=(TextView) convertView.findViewById(R.id.tvTypeDemand);
        tvExpDemande=(TextView) convertView.findViewById(R.id.tvExpDemande);

        tvHopital.setText(DemandeSang.getHospital_de_soin()+" ");
        tvTypeDemand.setText(DemandeSang.getTypeDemande()+" ");
        tvExpDemande.setText(DemandeSang.getExpirationDate()+" ");
        tvQteDemand.setText(DemandeSang.getQte_sang()+" L.");

        return convertView;
    }
}