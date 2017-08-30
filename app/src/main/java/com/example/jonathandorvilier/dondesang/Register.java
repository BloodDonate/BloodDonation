package com.example.jonathandorvilier.dondesang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonathandorvilier.dondesang.adapter.CustomSpinnerAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private EditText etBirthday, etUserName;
    private  EditText etTelephone;
    private Spinner spinner, spSexe;
    Spinner spin;
    private Button btCancel;
    private RadioButton rbMasculin, rbFeminin;
    private Button btRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName =(EditText)findViewById(R.id.etName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etUserName=(EditText)findViewById(R.id.etUserName);


        etBirthday=(EditText)findViewById(R.id.etBirthday);
        etTelephone=(EditText)findViewById(R.id.etTelephone);
        spinner=(Spinner) findViewById(R.id.spinnerBloodGroup);
        spSexe=(Spinner) findViewById(R.id.spSexe);
        btCancel=(Button) findViewById(R.id.btCancel);
        btRegister=(Button)findViewById(R.id.btRegister);


        btRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.btRegister:
                        Toast.makeText(Register.this, ""+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            /*if (etName.getText().toString().equals("") && etTelephone.getText().toString().equals("") && etBirthday.getText().toString().equals("") && etUserName.getText().toString().equals("") && etPassword.getText().toString().equals("")) {
                                Toast.makeText(Register.this, "Des champs sont vides", Toast.LENGTH_SHORT).show();
                            } else {
                               saveInfoUser();
                               // Toast.makeText(Register.this, etName.getText().toString() +" et "+ etTelephone.getText().toString()+" et "+ etBirthday.getText().toString()+" et "+ etUserName.getText().toString()+" et "+etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                            } */

                        break;
                }
            }
        });



        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner) findViewById(R.id.spinnerBloodGroup);
        spin.setOnItemSelectedListener(this);

        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getApplicationContext(),flags,BloodGroup);
        spin.setAdapter(customSpinnerAdapter);
    }

    private void saveInfoUser() {
        String url = "http://tipeyizanpam.esy.es/blood_donation/bd_register_user.php";
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("nom_user", etName.getText().toString());
        params.put("telephone_user", etTelephone.getText().toString());
        params.put("birthday_user", etBirthday.getText().toString());
        params.put("sexe_user", spSexe.getSelectedItem().toString());
        params.put("gsanguin_user", spinner.getSelectedItem().toString());
        params.put("username", etUserName.getText().toString());
        params.put("userpass", etPassword.getText().toString());
        client.post(url,params, new JsonHttpResponseHandler(){



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {

                    Object objectlogin = response.get("saveUser");
                    if (objectlogin instanceof JSONArray) {
                        articleJsonResults = response.getJSONArray("saveUser");
                       /* Intent i = new Intent(Register.this, MainActivity.class);
                        startActivity(i);*/
                        Toast.makeText(Register.this, "save user", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("echec: ",responseString.toString() );
                Toast.makeText(Register.this, "Verifier nom et mot de passe...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), BloodGroup[position], Toast.LENGTH_LONG).show();
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
}
