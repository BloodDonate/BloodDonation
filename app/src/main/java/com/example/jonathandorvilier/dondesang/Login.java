package com.example.jonathandorvilier.dondesang;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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

import cz.msebera.android.httpclient.Header;

public class Login extends ActionBarActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private TextView tvRegisterLink;
    int iVar= 0;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progress = new ProgressDialog(this);
        progress.setTitle("Se connecter");
        progress.setMessage("Verification en cours...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);

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
                    try {
                        progress.show();
                        getData();
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
                break;

            case  R.id.tvRegisterLink:

                        startActivity(new Intent(this, Register.class));

                break;
        }

    }

    private void getData() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException {
        String url = "http://astruitier.com/blood_donation/bd_login.php";

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
        params.put("user_name", etUsername.getText().toString());
         params.put("user_password", etPassword.getText().toString());
                client.post(url,params, new JsonHttpResponseHandler(){



                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        JSONArray articleJsonResults = null;
                        try {
                                Object objectlogin = response.get("login");

                                if (objectlogin instanceof JSONArray) {
                                    articleJsonResults = response.getJSONArray("login");
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    editor = sharedPreferences.edit();
                                    editor.putString("id_user", articleJsonResults.getJSONObject(0).getString("id_user"));
                                    editor.putString("nom_user", articleJsonResults.getJSONObject(0).getString("nom_user"));
                                    editor.putString("telephone_user", articleJsonResults.getJSONObject(0).getString("telephone_user"));
                                    editor.putString("birthday_user", articleJsonResults.getJSONObject(0).getString("birthday_user"));
                                    editor.putString("sexe_user", articleJsonResults.getJSONObject(0).getString("sexe_user"));
                                    editor.putString("gsanguin_user", articleJsonResults.getJSONObject(0).getString("gsanguin_user"));
                                    editor.putString("username", articleJsonResults.getJSONObject(0).getString("username"));
                                    editor.apply();
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        finishAffinity();
                                    }else{
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                                    }
                                    startActivity(i);
                                    progress.dismiss();
                                }else{
                                    progress.dismiss();
                                    Toast.makeText(Login.this, "erreur systeme, essayer a nouveau....", Toast.LENGTH_SHORT).show();
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("echec: ",responseString.toString() );
                        progress.dismiss();
                        Toast.makeText(Login.this, "Verifier nom et mot de passe...", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}







