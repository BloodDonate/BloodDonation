package com.example.jonathandorvilier.dondesang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.jonathandorvilier.dondesang.adapter.VosDonsAdapter;
import com.example.jonathandorvilier.dondesang.model.VosDons;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VosDonsActivity extends AppCompatActivity {

    ArrayList<VosDons> dons;
    VosDonsAdapter adapter;
    ProgressBar progress;
    ListView lvVosDons;
    String id_user;
    private SwipeRefreshLayout swiperefresh;
    SharedPreferences sharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vos_dons);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vos Dons :");

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        id_user=sharedPreferences.getString("id_user", null);

        lvVosDons = (ListView) findViewById(R.id.lvDemande);
        progress = (ProgressBar ) findViewById(R.id.progress);
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progress.setVisibility(View.VISIBLE);
                getData();
                swiperefresh.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swiperefresh.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);

        progress.setVisibility(View.VISIBLE);
        getData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getData() {
        //set adapter
        dons = new ArrayList<>();
        adapter = new VosDonsAdapter(getApplicationContext(), dons);
        lvVosDons.setAdapter(adapter);

        String url = "http://astruitier.com/blood_donation/liste_vos_dons.php?id_user="+id_user;

        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
        client.get(url, new JsonHttpResponseHandler(){


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {
                    articleJsonResults = response.getJSONArray("response");
                    adapter.addAll(VosDons.fromJsonArray(articleJsonResults));
                    adapter.notifyDataSetChanged();
                    Log.d("Debug", dons.toString());
                    progress.setVisibility(View.GONE);
                }catch (JSONException e){
                    e.printStackTrace();
                    getData();
                }
            }



            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("echec: ",responseString.toString() );
                getData();
            }
        });
    }

}
