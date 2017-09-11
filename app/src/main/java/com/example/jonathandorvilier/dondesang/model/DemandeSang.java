package com.example.jonathandorvilier.dondesang.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jonathan Dorvilier on 8/25/2017.
 */

public class DemandeSang {
    String id_user;
    String id_demande;
    String qte_sang;
    String gsanguin;
    String dr_reference;
    String hospital_de_soin;
    String nom_user;
    String expirationDate;
    String typeDemande;

    public DemandeSang() {
           }

    public String getId_demande() {
        return id_demande;
    }

   public void setId_demande(String id_demande) {
                this.id_demande = id_demande;
            }
    public String getId_user() {
        return id_user;
    }

     public void setId_user(String id_user) {
                this.id_user = id_user;
            }

    public String getQte_sang() {
        return qte_sang;
    }

     public void setQte_sang(String qte_sang) {
                this.qte_sang = qte_sang;
           }

    public String getGsanguin() {
        return gsanguin;
    }

    public void setGsanguin(String gsanguin) {
                this.gsanguin = gsanguin;
            }

    public String getDr_reference() {
        return dr_reference;
    }

     public void setDr_reference(String dr_reference) {
                this.dr_reference = dr_reference;
            }

    public String getHospital_de_soin() {
        return hospital_de_soin;
    }

     public void setHospital_de_soin(String hospital_de_soin) {
                this.hospital_de_soin = hospital_de_soin;
            }

             public String getNom_user() {
               return nom_user;
            }

            public void setNom_user(String nom_user) {
                this.nom_user = nom_user;
            }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

             public DemandeSang(JSONObject jsonObject) {
                try {
                        //this.id_demande = jsonObject.getString("id_user");
                               this.id_user = jsonObject.getString("id_user");
                        this.qte_sang = jsonObject.getString("qte_sang");
                       this.gsanguin = jsonObject.getString("gsanguin");
                       this.dr_reference = jsonObject.getString("dr_reference");
                        this.hospital_de_soin = jsonObject.getString("hospital_de_soin");
                        this.nom_user = jsonObject.getString("nom_user");

                        this.expirationDate = jsonObject.getString("expirationDate");
                        this.typeDemande = jsonObject.getString("typeDemande");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

            public static ArrayList<DemandeSang> fromJsonArray(JSONArray array){
                ArrayList<DemandeSang> results = new ArrayList<>();
                for(int x= 0; x < array.length(); x++){
                        try{
                                results.add(new DemandeSang(array.getJSONObject(x)));
                            }catch(JSONException e){
                                e.printStackTrace();
                            }
                   }
               return results;
           }


}
