package com.tesji.edu.optica;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tesji.edu.optica.com.tesji.edu.opticamodelo.OpticaSQLite;

public class actualizarEliminarLentes extends AppCompatActivity {

    EditText txtNumeroArticulo;
    EditText txtMarca;
    EditText txtModelo;
    EditText txtMica;
    EditText txtPrecio;
    EditText txtGenero;
    EditText txtEstilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_eliminar_lentes);

        txtNumeroArticulo = (EditText)findViewById(R.id.numeroArticulo);
        txtMarca = (EditText)findViewById(R.id.marca);
        txtModelo = (EditText)findViewById(R.id.modelo);
        txtMica = (EditText)findViewById(R.id.mica);
        txtPrecio = (EditText)findViewById(R.id.precio);
        txtGenero = (EditText)findViewById(R.id.genero);
        txtEstilo = (EditText)findViewById(R.id.estilo);
    }
    public void buscar(View v){
        OpticaSQLite conexion = new OpticaSQLite(this, "agendaLentes.db", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String id = txtNumeroArticulo.getText().toString();

        Cursor fila = db.rawQuery("SELECT * FROM lentes WHERE idLente="+id,null);

        if(fila.moveToFirst()){
            txtMarca.setText(fila.getString(1));
            txtModelo.setText(fila.getString(2));
            txtMica.setText(fila.getString(3));
            txtPrecio.setText(fila.getString(4));
            txtGenero.setText(fila.getString(5));
            txtEstilo.setText(fila.getString(6));
        }else{
            Toast.makeText(this, "No existe aiticulo con el ID: "+id, Toast.LENGTH_LONG).show();
        }
        db.close();
    }
    public void actualizar(View v){
        OpticaSQLite conexion = new OpticaSQLite(this, "agendaLentes.db", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String id = txtNumeroArticulo.getText().toString();
        String marca = txtMarca.getText().toString();
        String modelo = txtModelo.getText().toString();
        String mica = txtMica.getText().toString();
        String precio = txtPrecio.getText().toString();
        String genero = txtGenero.getText().toString();
        String estilo = txtEstilo.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("marca", marca);
        registro.put("modelo", modelo);
        registro.put("tipomica", mica);
        registro.put("precio", precio);
        registro.put("genero", genero);
        registro.put("tipolente", estilo);

        int cant = db.update("lentes", registro, "idLente="+id, null);
        db.close();

        if(cant==1){
            Toast.makeText(this, "Se modificó Lentes con Clave= "+id, Toast.LENGTH_LONG).show();
            limpiar();
        }else{
            Toast.makeText(this, "No existe Lentes con Clave= "+id, Toast.LENGTH_LONG).show();
        }
        db.close();
    }
    private void limpiar(){
        txtMarca.setText(null);
        txtModelo.setText(null);
        txtMica.setText(null);
        txtPrecio.setText(null);
        txtGenero.setText(null);
        txtEstilo.setText(null);
    }
    public void eliminar(View v){
        OpticaSQLite conexion = new OpticaSQLite(this, "agendaLentes.db", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String id = txtNumeroArticulo.getText().toString();

        int cant = db.delete("lentes", "idLente="+id, null);
        db.close();

        if(cant==1){
            Toast.makeText(this, "Se Eliminó los lentes con Clave: "+id, Toast.LENGTH_LONG).show();
            limpiar();
        }
        else{
            Toast.makeText(this, "No se puede borrar porque no existe los lentes", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

}
