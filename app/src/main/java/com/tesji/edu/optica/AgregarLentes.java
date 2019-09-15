package com.tesji.edu.optica;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tesji.edu.optica.com.tesji.edu.opticamodelo.OpticaSQLite;

public class AgregarLentes extends AppCompatActivity {

    EditText txtMarca;
    EditText txtModelo;
    EditText txtMica;
    EditText txtPrecio;
    EditText txtGenero;
    EditText txtEstilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_lentes);

        txtMarca = (EditText)findViewById(R.id.etMarca);
        txtModelo = (EditText)findViewById(R.id.etModelo);
        txtMica = (EditText)findViewById(R.id.etMica);
        txtPrecio = (EditText)findViewById(R.id.etPrecio);
        txtGenero = (EditText)findViewById(R.id.etGenero);
        txtEstilo = (EditText)findViewById(R.id.etEstilo);

    }

    public void Agregar(View vista){

        OpticaSQLite conexion = new OpticaSQLite(this,"agendaLentes.db",null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String marca = txtMarca.getText().toString();
        String modelo = txtModelo.getText().toString();
        String mica = txtMica.getText().toString();
        String precio = txtPrecio.getText().toString();
        String genero = txtGenero.getText().toString();
        String estilos = txtEstilo.getText().toString();

        if (marca.trim().length() == 0) {
            txtMarca.setError("Ingresa marca");
            txtMarca.requestFocus();
        }else if(modelo.trim().length()==0){
            txtModelo.setError("ingrese modelo");
            txtModelo.requestFocus();
        }else if(mica.trim().length() == 0){
            txtMica.setError("Ingrese mica");
            txtMica.requestFocus();
        }else if(precio.trim().length() == 0) {
            txtPrecio.setError("Ingrese precio");
            txtPrecio.requestFocus();
        }else if (genero.trim().length() == 0){
            txtGenero.setError("Ingrese genero");
            txtGenero.requestFocus();
        }else if(estilos.trim().length() == 0){
            txtEstilo.setError("ingrese estilo");
            txtEstilo.requestFocus();
        }

        ContentValues registro = new ContentValues();

        registro.put("marca",marca);
        registro.put("modelo",modelo);
        registro.put("tipomica",mica);
        registro.put("precio",precio);
        registro.put("genero",genero);
        registro.put("tipolente",estilos);

        db.insert("lentes",null,registro);

        Toast.makeText(this, "Lentes correctamente almacenado",Toast.LENGTH_SHORT).show();

        txtEstilo.setText(null);
        txtGenero.setText(null);
        txtPrecio.setText(null);
        txtMica.setText(null);
        txtMarca.setText(null);
        txtModelo.setText(null);

        conexion.close();
    }
    public void Regresar(View vista){
        finish();
    }
}
