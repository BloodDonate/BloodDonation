package com.example.jonathandorvilier.dondesang.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathandorvilier.dondesang.R;
import com.example.jonathandorvilier.dondesang.model.DemandeSang;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jonathan Dorvilier on 8/25/2017.
 */

public class DemandeAdapter extends ArrayAdapter<DemandeSang> {

    TextView tvNomUser,tvQteSang,tvGsanguin,tvDrReference,tvHopital,tvTypeDemand,tvExpDate;
    Button btnDonner,shareDemande;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    AlertDialog.Builder builder;
    AlertDialog alert;
    ProgressDialog progress;
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

                sharedPreferences = getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

                                tvNomUser=(TextView) convertView.findViewById(R.id.tvNomUser);
               tvQteSang=(TextView) convertView.findViewById(R.id.tvQteSang);
               tvGsanguin=(TextView) convertView.findViewById(R.id.tvGsanguin);
               tvDrReference=(TextView) convertView.findViewById(R.id.tvDrReference);
               tvHopital=(TextView) convertView.findViewById(R.id.tvHopital);
                tvExpDate=(TextView) convertView.findViewById(R.id.tvExpDate);
               tvTypeDemand=(TextView) convertView.findViewById(R.id.tvTypeDemand);
               btnDonner=(Button) convertView.findViewById(R.id.btnDonner);
                shareDemande=(Button) convertView.findViewById(R.id.shareDemande);

                tvNomUser.setText(demandeSang.getNom_user());
                tvQteSang.setText(demandeSang.getQte_sang()+" L");
                tvGsanguin.setText(demandeSang.getGsanguin());
                tvDrReference.setText(demandeSang.getDr_reference());
                tvHopital.setText(demandeSang.getHospital_de_soin());

                tvExpDate.setText(demandeSang.getExpirationDate());
                tvTypeDemand.setText(demandeSang.getTypeDemande());
                
               btnDonner.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 sharedPreferences.getBoolean("eligibleUserDon", false);
                     if(sharedPreferences.getBoolean("eligibleUserDon", false)== false){
                         Toast.makeText(getContext(), "Vous ne pouvez pas participer, verifier le questionnaire....", Toast.LENGTH_SHORT).show();
                     }else if(sharedPreferences.getBoolean("eligibleUserDon", false)== true){
                         if(sharedPreferences.getString("id_user", null)==demandeSang.getId_user()){
                             Toast.makeText(getContext(), "Essayer avec une autre demande...", Toast.LENGTH_SHORT).show();
                         }else {
                             confirmDon(demandeSang);
                             Toast.makeText(getContext(), "Donner a: " + demandeSang.getNom_user(), Toast.LENGTH_SHORT).show();
                         }
                     }
             }
         });

                shareDemande.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBodyText = "Participer au collecte de sang de "+demandeSang.getNom_user()+".\n Groupe sanguin: "+ demandeSang.getGsanguin()+"\nPatient a l'hopital: "+demandeSang.getHospital_de_soin()+"\nDr:"+ demandeSang.getDr_reference()+"\n\n Telecharger l\'application...";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Don de sang");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                        v.getContext().startActivity(Intent.createChooser(sharingIntent, "Option de partage :"));
                    }
                });


                return convertView;
           }

    private void confirmDon(final DemandeSang demandeSang) {
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmer Don")
                .setMessage("êtes vous sur de participer à ce collecte?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        try {
                            progress = new ProgressDialog(getContext());
                            progress.setTitle("Collecte sang");
                            progress.setMessage("Confirmation en cours ");
                            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progress.setIndeterminate(true);
                            progress.show();
                            saveDonUser(dialog,demandeSang);
                        } catch (KeyStoreException e) {
                            e.printStackTrace();
                        } catch (CertificateException e) {
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (UnrecoverableKeyException e) {
                            e.printStackTrace();
                        } catch (KeyManagementException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }

    private void saveDonUser(final DialogInterface dialog, DemandeSang demandeSang) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String url = "http://astruitier.com/blood_donation/register_don.php";
        /// We initialize a default Keystore
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
// We load the KeyStore
        trustStore.load(null, null);
// We initialize a new SSLSocketFacrory
        MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
// We set that all host names are allowed in the socket factory
        socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
// We initialize the Async Client
        AsyncHttpClient client = new AsyncHttpClient();
// We set the timeout to 30 seconds
        client.setTimeout(30*1000);
// We set the SSL Factory
        client.setSSLSocketFactory(socketFactory);

        RequestParams params = new RequestParams();
        params.put("id_demande", demandeSang.getId_demande());
        params.put("id_user_don", sharedPreferences.getString("id_user", null));
        params.put("date_check_don", dateFormat.format(date));
        client.post(url,params, new JsonHttpResponseHandler(){



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {

                    Object objectlogin = response.get("response");
                    if (objectlogin instanceof JSONArray) {
                        articleJsonResults = response.getJSONArray("response");
                        articleJsonResults.getJSONObject(0).getString("saveDon");

                        if(articleJsonResults.getJSONObject(0).getString("saveDon").equals("success")){
                            progress.dismiss();
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Confirmation réussi,\nAller dans le centre le plus proche pour participer a ce collecte...", Toast.LENGTH_SHORT).show();
                        }else if(articleJsonResults.getJSONObject(0).getString("saveDon").equals("echec")){
                            progress.dismiss();
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Une erreur s'est produite lors de l'envoi de cette demande...", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        progress.dismiss();
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Une erreur s'est produite lors de l'envoi de cette demande.", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    progress.dismiss();
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Une erreur s'est produite lors de l'envoi de cette demande, essayer a nouveau...", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //Log.d("echec: ",responseString.toString() );
                progress.dismiss();
                dialog.dismiss();
                Toast.makeText(getContext(), "Une erreur s'est produite lors de l'envoi de cette demande, essayer plus tard...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}