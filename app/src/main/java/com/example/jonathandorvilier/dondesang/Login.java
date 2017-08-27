package com.example.jonathandorvilier.dondesang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends ActionBarActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private TextView tvRegisterLink;
    /*
    private ListView lvDemande;

    ArrayList<DemandeSang> demandes;
    DemandeAdapter adapter; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        /*lvDemande = (ListView) findViewById(R.id.lvDemande);

//set adapter
        demandes = new ArrayList<>();
        adapter = new DemandeAdapter(this, demandes);
        lvDemande.setAdapter(adapter); */

        btLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btLogin:
                startActivity(new Intent(this, MainActivity.class));
                //getData();
                break;

            case  R.id.tvRegisterLink:

                startActivity(new Intent(this, Register.class));

                break;
        }

    }

    /*private void getData() {

        String url = "http://tipeyizanpam.esy.es/blood_donation/liste_demande.php";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {
                    articleJsonResults = response.getJSONArray("response");
                    adapter.addAll(DemandeSang.fromJsonArray(articleJsonResults));
                    adapter.notifyDataSetChanged();
                    Log.d("Debug", demandes.toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
               Log.d("echec: ",responseString.toString() );
            }
        });
    } */
}
