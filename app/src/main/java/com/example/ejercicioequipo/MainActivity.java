package com.example.ejercicioequipo;

import androidx.appcompat.app.AppCompatActivity;

//Importando la clase

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout tilSerie, tilDesc, tilValor;
    private Button btnGuardar, btnElim, btnVolver, btnAvanza;
    private TextView tvPagina;
    private Spinner spnTipo;
    private ArrayList<Equipo> equipos;
    private ArrayList<String> ListaTipos;
    private ArrayAdapter<String> AdaptadorTipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referencias();
        llenarTipos();
        eventos();

        AdaptadorTipos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaTipos);
        spnTipo.setAdapter(AdaptadorTipos);

    }
    //region Metodos
    private void llenadoArreglo(String desc, int valor, int serie, int tipo){
        Equipo llena = new Equipo();
        llena.setEquipo(serie, desc, valor, tipo);
        equipos.add(llena);

    }

    private void guardar() {
        int s, t, v;
        String d;
        s = Integer.parseInt(tilSerie.getEditText().getText().toString());
        v = Integer.parseInt(tilValor.getEditText().getText().toString());
        t = (int) spnTipo.getSelectedItemId();
        d = tilDesc.getEditText().getText().toString();
        llenadoArreglo(d, v, s, t);
        //Toast.makeText(this, "Debe Ingresar los datos de el producto!!", Toast.LENGTH_SHORT).show();
    }

    private void setTextos(){
        tilSerie.getEditText().setText("");
        tilValor.getEditText().setText("");
        tilDesc.getEditText().setText("");
        spnTipo.setSelection(0);
        String texto = 0 + "/" + equipos.size();
        tvPagina.setText(texto);
    }

    private void avanzar(){
        Equipo E;
        int indice = getIndiceDePagina();
        //Log.d("TAG_", "ARREGLO EQUIPO EN INDICE INDICADO - "+equipos.get(indice));
        if(indice < equipos.size()) {
            E = equipos.get(indice);
            String s, d, v;
            s = String.valueOf(E.getSerie());
            d = String.valueOf(E.getDescripcion());
            v = String.valueOf(E.getValor());
            tilSerie.getEditText().setText(s);
            tilDesc.getEditText().setText(d);
            tilValor.getEditText().setText(v);
            int tipo = E.getTipo();
            spnTipo.setSelection(tipo);
            String texto = (indice + 1) + "/" + equipos.size();
            tvPagina.setText(texto);
        }
        else{
            Toast.makeText(this, "No existen mas Productos", Toast.LENGTH_SHORT).show();
        }
    }

    private void retroceder(){
        Equipo E;
        int indice = getIndiceDePagina();
        if (indice > 1){
            indice = indice - 2;
            E = equipos.get(indice);
            String s, d, v;
            s = String.valueOf(E.getSerie());
            d = String.valueOf(E.getDescripcion());
            v = String.valueOf(E.getValor());
            tilSerie.getEditText().setText(s);
            tilDesc.getEditText().setText(d);
            tilValor.getEditText().setText(v);
            int tipo = E.getTipo();
            spnTipo.setSelection(tipo);
            String texto = (indice+1) + "/" + equipos.size();
            tvPagina.setText(texto);
        }
        else{
            Toast.makeText(this, "No existen mas Productos", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminar(){
        if (equipos.size() > 0) {
            int indice = getIndiceDePagina()-1;
            equipos.remove(indice);
        }
        else {
            Toast.makeText(this, "Primero debe agregar un producto", Toast.LENGTH_SHORT).show();
        }
    }
    private int getIndiceDePagina(){
        String i = tvPagina.getText().toString();
        i = i.substring(0,1);
        int indice = Integer.parseInt(i);
        Log.d("TAG_", "Indice "+ indice);
        //indice = indice + 1;
        //Log.d("TAG_", "Nuevo Indice "+indice);
        return indice;
    }
    //endregion


    //region Eventos y Referencias
    //region Eventos
    private void eventos(){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
                setTextos();
            }
        });

        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eliminar();
                setTextos();
            }
        });

        btnAvanza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avanzar();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retroceder();
            }
        });
    }
    //endregion

    //region Referencias
    private void referencias() {
        tilSerie = findViewById(R.id.tilSerie);
        tilDesc = findViewById(R.id.tilDescripcion);
        tilValor = findViewById(R.id.tilValor);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnElim = findViewById(R.id.btnEliminar);
        btnAvanza = findViewById(R.id.btnAvan);
        btnVolver = findViewById(R.id.btnRetro);
        tvPagina = findViewById(R.id.tvPagina);
        equipos = new ArrayList<Equipo>();
        spnTipo = findViewById(R.id.spinner);
        ListaTipos = new ArrayList<String>();
    }

    private void llenarTipos(){
        ListaTipos.add("Seleccione un Tipo de producto");
        ListaTipos.add("Relleno");
        ListaTipos.add("Macizo");
        ListaTipos.add("Bañado");
        ListaTipos.add("Masas");
    }
    //endregion
    //endregion
}