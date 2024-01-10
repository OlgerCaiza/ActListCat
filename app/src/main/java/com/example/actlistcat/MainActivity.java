package com.example.actlistcat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Llenar combo box
        //Paso 1 Data
        final String[] datos = new String[]{"Pais","Capital","GeoPt"};
        //Paso 2 Adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        //Paso 3 Vista
        Spinner cmbOpciones = (Spinner)findViewById(R.id.cbEstudiantes);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(this);

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos2 = new HashMap<String, String>();
        WebService ws= new WebService("http://www.geognos.com/api/en/countries/service_name/counry_code_or_ip.ouput_format",
                datos2, MainActivity.this, MainActivity.this);
        ws.execute("GET");



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txtItem = (TextView)findViewById(R.id.textCatego);
        txtItem.setText("Seleccionado: " + parent.getItemAtPosition(position));
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList listaLugaresTuristicos = new ArrayList();


        //Parceo JSON
        JSONObject lista= new JSONObject(result);
        JSONArray JSONlista = lista.getJSONArray("data");



        listaLugaresTuristicos = lugarTuristico.JsonObjectsBuild(JSONlista);
        AdaptadorLugaresTuristico adaptadorLugares = new AdaptadorLugaresTuristico(this, listaLugaresTuristicos);


        //Paso3
        ListView lstOpciones = (ListView) findViewById(R.id.lstLugar);
        lstOpciones.setAdapter(adaptadorLugares);


    }
}