package com.tesji.edu.optica;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tesji.edu.optica.com.tesji.edu.opticamodelo.OpticaSQLite;

public class comprarLentes extends AppCompatActivity {

    EditText txtEmail;
    EditText txtNombre;
    EditText txtApellidos;
    EditText txtTelefono;
    EditText txtFecha;
    EditText txtgradD;
    EditText txtgradi;
    Spinner cmbNumLentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_lentes);

        txtEmail = (EditText)findViewById(R.id.etEmail);
        txtNombre = (EditText)findViewById(R.id.etNombre);
        txtApellidos = (EditText)findViewById(R.id.etApellidos);
        txtTelefono = (EditText)findViewById(R.id.etTelefono);
        txtFecha = (EditText)findViewById(R.id.etFecha);
        txtgradD = (EditText)findViewById(R.id.etgradd);
        txtgradi = (EditText)findViewById(R.id.etgradi);
        cmbNumLentes = (Spinner)findViewById(R.id.spLentes);

        llenarLentes();
    }

    private  void llenarLentes() {
        OpticaSQLite conexion = new OpticaSQLite(
                this,
                "agendaLentes.db",
                null, 1);

        SQLiteDatabase bd = conexion.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM lentes", null);
        int cont = fila.getCount();

        String array[] = new String[cont + 1];
        array[0] = "--Seleccione Lentes--";
        fila.moveToFirst();

        int x = 1;

        while (x <= cont) {
            array[x] = fila.getString(2);
            x++;
            fila.moveToNext();
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        cmbNumLentes.setAdapter(adaptador);
        bd.close();
    }
    public void comprarLentes(View v) {
        OpticaSQLite conexion = new OpticaSQLite(
                this,
                "agendaLentes.db",
                null, 1);

        SQLiteDatabase bd = conexion.getReadableDatabase();
        //Extraer la clave de la carrera
        String buscarLente = cmbNumLentes.getSelectedItem().toString();
        Cursor lente = bd.rawQuery("SELECT idLente FROM lentes WHERE modelo='" + buscarLente + "'", null);
        int cont = lente.getCount();

        String array[] = new String[cont];
        lente.moveToFirst();

        int x = 0;

        while (x < cont) {
            array[x] = lente.getString(0);
            x++;
            lente.moveToNext();
        }

//________________________  Guardando Alumno

        if (cont > 0) {  //Validando que si seleccionó Carrera
            String email = txtEmail.getText().toString();
            if(email.trim().length()== 0){
                txtEmail.setError("ingrese Email");
            }
            String nombre = txtNombre.getText().toString();
            String apellido = txtApellidos.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String fecha = txtFecha.getText().toString();
            String gradd = txtgradD.getText().toString();
            String gradi = txtgradi.getText().toString();
            String idLente = array[0];//id extraido de la tabla depto que coincide con el seleccionado del spiner

            ContentValues registro = new ContentValues();
            registro.put("email",email);
            registro.put("nombre", nombre);//Base datos
            registro.put("apellidos", apellido);
            registro.put("telefono", telefono);
            registro.put("fechacompra", fecha);
            registro.put("gradod", gradd);
            registro.put("gradoi", gradi);
            registro.put("idLente", idLente);

            bd.insert("comprador", null, registro);
            limpiar();

            Toast.makeText(this, "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Seleccione Lentes", Toast.LENGTH_LONG).show();
            cmbNumLentes.requestFocus();
        }
        bd.close();
    }
    private void limpiar(){
        txtEmail.setText(null);
        txtNombre.setText(null);
        txtApellidos.setText(null);
        txtTelefono.setText(null);
        txtFecha.setText(null);
        txtgradD.setText(null);
        txtgradi.setText(null);

        cmbNumLentes.setSelection(0);
        txtEmail.requestFocus();
    }
    public void regresar(View v) {
        finish();
    }
}
