package com.example.jonathandorvilier.dondesang.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jonathan Dorvilier on 8/25/2017.
 */

public class ServiceCenter implements Serializable {

   String id_centre;
    String nom_centre;
    String adresse_centre;
    String tel_centre;
    String long_centre;
    String lat_centre;
    String doc_ref_centre;

    //constructor to retrieve all the attributes we need
    public ServiceCenter (JSONObject jsonObject) throws JSONException{

        id_centre = jsonObject.getString("id_centre");
        nom_centre = jsonObject.getString("nom_centre");
        adresse_centre = jsonObject.getString("adresse_centre");
        tel_centre = jsonObject.getString("tel_centre");
        long_centre = jsonObject.getString("long_centre");
        lat_centre = jsonObject.getString("lat_centre");
        doc_ref_centre = jsonObject.getString("doc_ref_centre");


    }

    public String getId_centre() {
        return id_centre;
    }

    public String getNom_centre() {
        return nom_centre;
    }

    public String getAdresse_centre() {
        return adresse_centre;
    }

    public String getTel_centre() {
        return tel_centre;
    }

    public String getLong_centre() {
        return long_centre;
    }

    public String getLat_centre() {
        return lat_centre;
    }

    public String getDoc_ref_centre() {
        return doc_ref_centre;
    }

    //convert Json object into ArrayList

    public static ArrayList<ServiceCenter> fromJSONArray(JSONArray array){

        ArrayList<ServiceCenter> results = new ArrayList<>();

        for(int i=0; i<array.length(); i++){
            try {
                results.add(new ServiceCenter(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;


    }
}
