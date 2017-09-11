package com.example.jonathandorvilier.dondesang;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //AdapterView.OnItemSelectedListener

    String[] BloodGroup = {"O+", "O-", "AB+", "AB-", "A+", "A-", "B+", "B-"};
    int flags[] = {R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang};


    private EditText etName;
    private EditText etPassword;
    private EditText etEmail;
    private CheckBox chBoxMale;
    private CheckBox chBoxFemale;
    private static EditText etBirthday;
    private static EditText etUserName;
    private  EditText etTelephone;
    private Spinner spinnerBloodGroup, spSexe;
    Spinner spin;
    private Button btCancel;
    private RadioButton rbMasculin, rbFeminin;
    private Button btRegister;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progress = new ProgressDialog(this);
        progress.setTitle("Creer Compte");
        progress.setMessage("Enregistrement en cours ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);

        etName =(EditText)findViewById(R.id.etName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etUserName=(EditText)findViewById(R.id.etUserName);
        etBirthday=(EditText)findViewById(R.id.etBirthday);
        etTelephone=(EditText)findViewById(R.id.etTelephone);
        spinnerBloodGroup=(Spinner) findViewById(R.id.spinnerBloodGroup);
        spSexe=(Spinner) findViewById(R.id.spSexe);
        btCancel=(Button) findViewById(R.id.btCancel);
        btRegister=(Button)findViewById(R.id.btRegister);


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        /*spin = (Spinner) findViewById(R.id.spinnerBloodGroup);
        //spin.setOnItemSelectedListener(this);

        final CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getApplicationContext(),flags,BloodGroup);
        spin.setAdapter(customSpinnerAdapter); */

        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.btRegister:
                        //Toast.makeText(Register.this, "...."+spinnerBloodGroup.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                        if(spSexe.getSelectedItem().toString().equals("Choisir")){
                            Toast.makeText(Register.this, "selectionner sexe", Toast.LENGTH_SHORT).show();
                        } else if(spinnerBloodGroup.getSelectedItem().toString().equals("Choisir")){
                            Toast.makeText(Register.this, "selectionner Groupe sanguin", Toast.LENGTH_SHORT).show();
                        }else if (etBirthday.getText().toString().matches("\\d{4}-\\d{2}-\\d{2}")){
                            if (etName.getText().toString().equals("") || etTelephone.getText().toString().equals("") || etUserName.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                                Toast.makeText(Register.this, "Des champs sont vides", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    progress.show();
                                    saveInfoUser();
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
                                // Toast.makeText(Register.this, etName.getText().toString() +" et "+ etTelephone.getText().toString()+" et "+ etBirthday.getText().toString()+" et "+ etUserName.getText().toString()+" et "+etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Register.this, "Verifier date entrer...", Toast.LENGTH_SHORT).show();
                        }

                        break;
                }
            }
        });

    }

    private void saveInfoUser() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException {
        String url = "http://astruitier.com/blood_donation/bd_register_user.php";
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
        params.put("nom_user", etName.getText().toString());
        params.put("telephone_user", etTelephone.getText().toString());
        params.put("birthday_user", etBirthday.getText().toString());
        params.put("sexe_user", spSexe.getSelectedItem().toString());
        params.put("gsanguin_user", spinnerBloodGroup.getSelectedItem().toString());
        params.put("username", etUserName.getText().toString());
        params.put("userpass", etPassword.getText().toString());
        client.post(url,params, new JsonHttpResponseHandler(){



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {

                    Object objectlogin = response.get("response");
                    if (objectlogin instanceof JSONArray) {
                        articleJsonResults = response.getJSONArray("response");
                        articleJsonResults.getJSONObject(0).getString("saveUser");

                        if(articleJsonResults.getJSONObject(0).getString("saveUser").equals("success")){
                            Intent i = new Intent(Register.this, Login.class);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }else{
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                            }
                            startActivity(i);
                            progress.dismiss();
                        }else if(articleJsonResults.getJSONObject(0).getString("saveUser").equals("echec")){
                            progress.dismiss();
                            Toast.makeText(Register.this, "Essayer a nouveau, ce numero existe...", Toast.LENGTH_SHORT).show();
                        }
                    }


                } catch (JSONException e) {
                    progress.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("echec: ",responseString.toString() );
                progress.dismiss();
                Toast.makeText(Register.this, "Verifier nom et mot de passe...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
       // Toast.makeText(getApplicationContext(), BloodGroup[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            etBirthday.setText(simpleDateFormat.format(c.getTime()));
        }
    }
}
