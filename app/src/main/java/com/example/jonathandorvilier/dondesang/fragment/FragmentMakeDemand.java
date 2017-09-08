package com.example.jonathandorvilier.dondesang.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonathandorvilier.dondesang.R;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ingdjason on 9/7/17.
 */


public class FragmentMakeDemand extends android.support.v4.app.DialogFragment {

    EditText edtQteSang;
    EditText edtDocteurRef;
    EditText edtHopitalSoin;
    static EditText edtDateExp;
    Spinner spinnerBloodGroup, spinnerTypeDemande;
    Button btnSaveDemande;
    ProgressDialog progress;
    SharedPreferences sharedPreferences ;
    public FragmentMakeDemand() {
        // Required empty public constructor
    }
    public static FragmentMakeDemand newInstance(String title){
        FragmentMakeDemand frag = new FragmentMakeDemand();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_make_demand,parent,false);

        progress = new ProgressDialog(getActivity());
        progress.setTitle("Demande");
        progress.setMessage("Enregistrement en cours ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        sharedPreferences = getContext().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        edtQteSang =(EditText)  v.findViewById(R.id.edtQteSang);
        edtDocteurRef =(EditText)  v.findViewById(R.id.edtDocteurRef);
        edtHopitalSoin =(EditText)  v.findViewById(R.id.edtHopitalSoin);
        edtDateExp =(EditText)  v.findViewById(R.id.edtDateExp);
        spinnerTypeDemande =(Spinner)  v.findViewById(R.id.spinnerTypeDemande);
        spinnerBloodGroup =(Spinner)  v.findViewById(R.id.spinnerBloodGroup);
        btnSaveDemande =(Button)  v.findViewById(R.id.btnSaveDemande);

        edtDateExp.setKeyListener(null);
        edtDateExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        /*
        ALTER TABLE `demande_sang` ADD `type_demande` VARCHAR(50) NOT NULL AFTER `hospital_de_soin`, ADD `expiration_demande` DATE NOT NULL AFTER `type_demande`;
         */
        btnSaveDemande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerTypeDemande.getSelectedItem().toString();
                spinnerBloodGroup.getSelectedItem().toString();
                edtQteSang.getText().toString();
                edtDocteurRef.getText().toString();
                edtHopitalSoin.getText().toString();
                edtDateExp.getText().toString();

                if(spinnerTypeDemande.getSelectedItem().toString().equals("Choisir")){
                    Toast.makeText(getActivity(), "Choisir groupe sanguin...", Toast.LENGTH_SHORT).show();
                }else{
                    if(edtQteSang.getText().toString()!=" " && edtDocteurRef.getText().toString() != " " && edtHopitalSoin.getText().toString() != " " && edtDateExp.getText().toString() != "0000-00-00"){
                        if(sharedPreferences.getString("id_user", null) != null){
                            try {
                                progress.show();
                                saveDemandSang(spinnerTypeDemande.getSelectedItem().toString(),spinnerBloodGroup.getSelectedItem().toString(),edtQteSang.getText().toString(),edtDocteurRef.getText().toString(),edtHopitalSoin.getText().toString(),edtDateExp.getText().toString());
                            } catch (KeyStoreException e) {
                                e.printStackTrace();
                            } catch (UnrecoverableKeyException e) {
                                e.printStackTrace();
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (KeyManagementException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (CertificateException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        Toast.makeText(getActivity(), "Champs vides...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title", "title");
        getDialog().setTitle(title);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

       /* @Override
        public void onDismiss(DialogInterface dialog) {
            edtStartDate.getText().toString();
            edtEndDate.getText().toString();
            spSortBy.getSelectedItem().toString();
            super.onDismiss(dialog);
        }*/

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            final Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            edtDateExp.setText(simpleDateFormat.format(c.getTime()));
        }
    }
    
    public void saveDemandSang(String typeDemande, String gSanguin, String qteSang, String docReference, String hopitalSoin, String expirationDate) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, IOException, CertificateException {
        String url = "http://astruitier.com/blood_donation/bd_register_demand.php";
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
        params.put("typeDemande", typeDemande);
        params.put("gSanguin", gSanguin);
        params.put("qteSang", qteSang);
        params.put("docReference", docReference);
        params.put("hopitalSoin", hopitalSoin);
        params.put("expirationDate", expirationDate);
        params.put("idUserDemand", sharedPreferences.getString("id_user", null));
        client.post(url,params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {

                    Object objectlogin = response.get("response");
                    if (objectlogin instanceof JSONArray) {
                        articleJsonResults = response.getJSONArray("response");
                        if(articleJsonResults.getJSONObject(0).getString("saveDemand").equals("success")){
                            successSave(1);
                        }else if(articleJsonResults.getJSONObject(0).getString("saveDemand").equals("echec")){
                            successSave(0);
                        }else{
                            successSave(0);
                        }
                    }

                } catch (JSONException e) {
                    successSave(0);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("echec: ",responseString.toString() );
                successSave(0);
            }
        });
    }

    private void successSave(int i) {
        if(i==1){
            progress.dismiss();
            FragmentMakeDemand.this.dismiss();
            Toast.makeText(getActivity(), "Merci, demande enregistrer...", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Erreur, essayer a nouveau...", Toast.LENGTH_SHORT).show();
            progress.dismiss();
        }
    }
}

