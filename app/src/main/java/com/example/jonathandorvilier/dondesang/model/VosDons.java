package com.example.jonathandorvilier.dondesang.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ingdjason on 9/11/17.
 */

public class VosDons {
    String id_don;
    String demande_ref_id;
    String date_check_don;
    String id_user_don;
    String donner_a;

    public VosDons() {
    }

    public String getId_don() {
        return id_don;
    }

    public void setId_don(String id_don) {
        this.id_don = id_don;
    }

    public String getDemande_ref_id() {
        return demande_ref_id;
    }

    public void setDemande_ref_id(String demande_ref_id) {
        this.demande_ref_id = demande_ref_id;
    }

    public String getDate_check_don() {
        return date_check_don;
    }

    public void setDate_check_don(String date_check_don) {
        this.date_check_don = date_check_don;
    }

    public String getId_user_don() {
        return id_user_don;
    }

    public void setId_user_don(String id_user_don) {
        this.id_user_don = id_user_don;
    }

    public String getDonner_a() {
        return donner_a;
    }

    public void setDonner_a(String donner_a) {
        this.donner_a = donner_a;
    }



    public VosDons(JSONObject jsonObject) {
        try {
            //this.id_demande = jsonObject.getString("id_user");
            this.id_don = jsonObject.getString("id_don");
            this.demande_ref_id = jsonObject.getString("demande_ref_id");
            this.date_check_don = jsonObject.getString("date_check_don");
            this.id_user_don = jsonObject.getString("id_user_don");
            this.donner_a = jsonObject.getString("donner_a");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<VosDons> fromJsonArray(JSONArray array){
        ArrayList<VosDons> results = new ArrayList<>();
        for(int x= 0; x < array.length(); x++){
            try{
                results.add(new VosDons(array.getJSONObject(x)));
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }


}
