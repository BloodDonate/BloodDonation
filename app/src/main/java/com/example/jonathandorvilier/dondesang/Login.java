package com.example.jonathandorvilier.dondesang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends ActionBarActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private TextView tvRegisterLink;
    int iVar= 0;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        etUsername = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        btLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        /*
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        Toast.makeText(Login.this, "new var: "+iVar, Toast.LENGTH_SHORT).show();
                        iVar++; }
                });
            }
        }, 2000, 2000); */
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btLogin:

                if(etUsername.getText().toString().equals("") && etPassword.getText().toString().equals("")){
                    Toast.makeText(Login.this, "un ou plusieurs champs sont vides...", Toast.LENGTH_SHORT).show();
                }else{
                    getData();
                }
                break;

            case  R.id.tvRegisterLink:

                        startActivity(new Intent(this, Register.class));

                break;
        }

    }

    private void getData() {
        String url = "http://tipeyizanpam.esy.es/blood_donation/bd_login.php";
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("user_name", etUsername.getText().toString());
         params.put("user_password", etPassword.getText().toString());
                client.post(url,params, new JsonHttpResponseHandler(){



                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        JSONArray articleJsonResults = null;
                        try {

                            Object objectlogin = response.get("response");
                            if (objectlogin instanceof JSONArray) {
                                articleJsonResults = response.getJSONArray("response");
                                Intent i = new Intent(Login.this, MainActivity.class);
                                /*i.putExtra("id_user", articleJsonResults.getJSONObject(0).getString("id_user"));
                                i.putExtra("nom_user", articleJsonResults.getJSONObject(0).getString("nom_user"));
                                i.putExtra("telephone_user", articleJsonResults.getJSONObject(0).getString("telephone_user"));
                                i.putExtra("birthday_user", articleJsonResults.getJSONObject(0).getString("birthday_user"));
                                i.putExtra("sexe_user", articleJsonResults.getJSONObject(0).getString("sexe_user"));
                                i.putExtra("gsanguin_user", articleJsonResults.getJSONObject(0).getString("gsanguin_user"));
                                i.putExtra("username", articleJsonResults.getJSONObject(0).getString("username")); */

                                editor = sharedPreferences.edit();
                                editor.putString("id_user", articleJsonResults.getJSONObject(0).getString("id_user"));
                                editor.putString("nom_user", articleJsonResults.getJSONObject(0).getString("nom_user"));
                                editor.putString("telephone_user", articleJsonResults.getJSONObject(0).getString("telephone_user"));
                                editor.putString("birthday_user", articleJsonResults.getJSONObject(0).getString("birthday_user"));
                                editor.putString("sexe_user", articleJsonResults.getJSONObject(0).getString("sexe_user"));
                                editor.putString("gsanguin_user", articleJsonResults.getJSONObject(0).getString("gsanguin_user"));
                                editor.putString("username", articleJsonResults.getJSONObject(0).getString("username"));
                                editor.apply();

                                startActivity(i);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("echec: ",responseString.toString() );
                        Toast.makeText(Login.this, "Verifier nom et mot de passe...", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}







