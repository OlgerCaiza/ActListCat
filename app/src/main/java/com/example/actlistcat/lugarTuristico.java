package com.example.actlistcat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class lugarTuristico {
    String Nombre;
    String Capital;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getGeoPt() {
        return GeoPt;
    }

    public void setGeoPt(String GeoPt) {
        GeoPt = GeoPt;
    }

    public String getUrlLogo() {
        return UrlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        UrlLogo = urlLogo;
    }



    public lugarTuristico(JSONObject a) throws JSONException {
        Nombre = a.getString("Nombre").toString();
        Capital= a.getString("Capital").toString() ;
        GeoPt = a.getString("GeoPt").toString() ;
        UrlLogo = "http://www.geognos.com/api/en/countries/flag/{alpha2code}.png" +
        a.getString("logo").toString();
    }
    public static ArrayList<lugarTuristico> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<lugarTuristico> lstLugares = new ArrayList<>();
        for (int i = 0; i < datos.length() && i<20; i++) {
            lstLugares.add(new lugarTuristico(datos.getJSONObject(i)));
        }
        return lstLugares;
    }
    String GeoPt; String UrlLogo;
}

