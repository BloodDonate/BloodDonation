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
import com.example.jonathandorvilier.dondesang.adapter.ServiceCenterAdapter;
import com.example.jonathandorvilier.dondesang.model.ServiceCenter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jonathan Dorvilier on 8/28/2017.
 */

public class FragmentServiceCenter extends Fragment {

    private ListView lvServiceCenter;
    ServiceCenterAdapter serviceCenterAdapter;
    ProgressBar  progressBar;
    ArrayList<ServiceCenter> serviceCenters;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String ARG_PAGE= "ARG_PAGE";

    public FragmentServiceCenter newInstance(int page){

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentServiceCenter fragment = new FragmentServiceCenter();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_service_center,parent,false);
        lvServiceCenter = (ListView) v.findViewById(R.id.lvServiceCenter);
        progressBar = (ProgressBar ) v.findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressBar.setVisibility(View.VISIBLE);
                getData();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        // Configure the refreshing colors
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);

        progressBar.setVisibility(View.VISIBLE);
        getData();

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getData(){

//set adapter
        serviceCenters = new ArrayList<>();
        serviceCenterAdapter = new ServiceCenterAdapter(getContext(), serviceCenters);
        lvServiceCenter.setAdapter(serviceCenterAdapter);

        String url = "http://tipeyizanpam.esy.es/blood_donation/liste_centre.php";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {
                    articleJsonResults = response.getJSONArray("response");
                    serviceCenterAdapter.addAll(ServiceCenter.fromJSONArray(articleJsonResults));
                    serviceCenterAdapter.notifyDataSetChanged();
                    Log.d("Debug", serviceCenters.toString());
                    progressBar.setVisibility(View.GONE);
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

