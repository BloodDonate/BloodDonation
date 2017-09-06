package com.example.jonathandorvilier.dondesang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.jonathandorvilier.dondesang.R;
import com.example.jonathandorvilier.dondesang.adapter.DemandeAdapter;
import com.example.jonathandorvilier.dondesang.model.DemandeSang;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jonathan Dorvilier on 8/27/2017.
 */

public class FragmentListeDemand extends Fragment {


     private ListView lvDemande;
    ArrayList<DemandeSang> demandes;
     DemandeAdapter adapter;
    ProgressBar progress;
   private SwipeRefreshLayout swiperefresh;

            public static final String ARG_PAGE = "ARG_PAGE";
     public static FragmentListeDemand newInstance(int page){

                        Bundle args = new Bundle();
                args.putInt(ARG_PAGE, page);
               FragmentListeDemand fragment = new FragmentListeDemand();
               fragment.setArguments(args);
                return fragment;
            }
     @Nullable
     @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
                View v = inflater.inflate(R.layout.fragment_list_demande,parent,false);
                lvDemande = (ListView) v.findViewById(R.id.lvDemande);
                progress = (ProgressBar ) v.findViewById(R.id.progress);
                swiperefresh = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);

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

                      return v;
           }


            @Override
    public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
           }


             private void getData() {
               //set adapter
                       demandes = new ArrayList<>();
             adapter = new DemandeAdapter(getContext(), demandes);
                lvDemande.setAdapter(adapter);

                       String url = "http://astruitier.com/blood_donation/liste_demande.php";

                        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
               client.get(url, new JsonHttpResponseHandler(){


                   @Override
                   public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                       JSONArray articleJsonResults = null;
                       try {
                           articleJsonResults = response.getJSONArray("response");
                           adapter.addAll(DemandeSang.fromJsonArray(articleJsonResults));
                           adapter.notifyDataSetChanged();
                           Log.d("Debug", demandes.toString());
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
