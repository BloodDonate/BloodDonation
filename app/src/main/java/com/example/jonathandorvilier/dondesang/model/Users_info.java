package com.example.jonathandorvilier.dondesang.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ingdjason on 8/26/17.
 */

public class Users_info {
    String id_user;
    String nom_user;
    String telephone_user;
    String birthday_user;
    String sexe_user;
    String gsanguin_user;
    String username;

    public Users_info(JSONObject jsonObject) {
        try {

            this.id_user = jsonObject.getString("id_user");
            this.nom_user = jsonObject.getString("nom_user");
            this.telephone_user = jsonObject.getString("telephone_user");
            this.birthday_user = jsonObject.getString("birthday_user");
            this.sexe_user = jsonObject.getString("sexe_user");
            this.gsanguin_user = jsonObject.getString("gsanguin_user");
            this.username = jsonObject.getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Users_info() {
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getTelephone_user() {
        return telephone_user;
    }

    public void setTelephone_user(String telephone_user) {
        this.telephone_user = telephone_user;
    }

    public String getBirthday_user() {
        return birthday_user;
    }

    public void setBirthday_user(String birthday_user) {
        this.birthday_user = birthday_user;
    }

    public String getSexe_user() {
        return sexe_user;
    }

    public void setSexe_user(String sexe_user) {
        this.sexe_user = sexe_user;
    }

    public String getGsanguin_user() {
        return gsanguin_user;
    }

    public void setGsanguin_user(String gsanguin_user) {
        this.gsanguin_user = gsanguin_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
