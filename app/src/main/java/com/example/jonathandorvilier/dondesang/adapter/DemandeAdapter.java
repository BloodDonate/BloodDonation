package com.example.jonathandorvilier.dondesang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathandorvilier.dondesang.R;
import com.example.jonathandorvilier.dondesang.model.DemandeSang;

import java.util.List;

/**
 * Created by Jonathan Dorvilier on 8/25/2017.
 */

public class DemandeAdapter extends ArrayAdapter<DemandeSang> {

    TextView tvNomUser;
    TextView tvQteSang;
    TextView tvGsanguin;
    TextView tvDrReference;
    TextView tvHopital;
    Button btnDonner;
    Button shareDemande;


    public DemandeAdapter(Context context, List<DemandeSang> demandeSangs){
                super(context, android.R.layout.simple_list_item_1, demandeSangs);
            }

            @Override
     public View getView(int position, View convertView, ViewGroup parent) {
               final DemandeSang demandeSang = this.getItem(position);

                        if(convertView==null){
                       LayoutInflater inflater = LayoutInflater.from(getContext());
                        convertView = inflater.inflate(R.layout.item_list_demand, parent, false);
                   }

                                tvNomUser=(TextView) convertView.findViewById(R.id.tvNomUser);
               tvQteSang=(TextView) convertView.findViewById(R.id.tvQteSang);
               tvGsanguin=(TextView) convertView.findViewById(R.id.tvGsanguin);
               tvDrReference=(TextView) convertView.findViewById(R.id.tvDrReference);
               tvHopital=(TextView) convertView.findViewById(R.id.tvHopital);
               btnDonner=(Button) convertView.findViewById(R.id.btnDonner);
                shareDemande=(Button) convertView.findViewById(R.id.shareDemande);

                tvNomUser.setText(demandeSang.getNom_user());
                tvQteSang.setText(demandeSang.getQte_sang()+" L");
                tvGsanguin.setText(demandeSang.getGsanguin());
                tvDrReference.setText(demandeSang.getDr_reference());
                tvHopital.setText(demandeSang.getHospital_de_soin());

               btnDonner.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                                Toast.makeText(getContext(), "Donner a: "+ demandeSang.getNom_user(), Toast.LENGTH_SHORT).show();
                            }
         });

                shareDemande.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBodyText = "Participer au collecte de sang de "+demandeSang.getNom_user()+".\n Groupe sanguin: "+ demandeSang.getGsanguin()+"\nPatient a l'hopital: "+demandeSang.getHospital_de_soin()+"\nDr:"+ demandeSang.getDr_reference();
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Don de sang");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                        v.getContext().startActivity(Intent.createChooser(sharingIntent, "Option de partage :"));
                    }
                });


                return convertView;
           }
 }