package com.example.jonathandorvilier.dondesang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathandorvilier.dondesang.R;
import com.example.jonathandorvilier.dondesang.model.ServiceCenter;

import java.util.ArrayList;

/**
 * Created by Jonathan Dorvilier on 8/25/2017.
 */

public class ServiceCenterAdapter extends ArrayAdapter<ServiceCenter>{
    TextView tvNomCentre;
    TextView    tvAdresseCentre;
    TextView tvTelephoneCentre;
    TextView    tvDrReference;
    ImageView  ivMap_service;



    public ServiceCenterAdapter(Context context, ArrayList<ServiceCenter> serviceCenters) {
        super(context, android.R.layout.simple_list_item_1, serviceCenters);
            }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ServiceCenter serviceCenters = this.getItem(position);

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_service_center, parent, false);
        }

        tvNomCentre=(TextView) convertView.findViewById(R.id.tvNomCentre);
        tvAdresseCentre=(TextView) convertView.findViewById(R.id.tvAdresseCentre);
        tvTelephoneCentre=(TextView) convertView.findViewById(R.id.tvTelephoneCentre);
        tvDrReference=(TextView) convertView.findViewById(R.id.tvDrReference);
        ivMap_service=(ImageView) convertView.findViewById(R.id.ivMap_service);

        tvNomCentre.setText(serviceCenters.getNom_centre());
        tvAdresseCentre.setText(serviceCenters.getAdresse_centre());
        tvTelephoneCentre.setText(serviceCenters.getTel_centre());
        tvDrReference.setText(serviceCenters.getDoc_ref_centre());

        ivMap_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Map centre a: ", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public void notifyDataSetChanged() {
    }
}
