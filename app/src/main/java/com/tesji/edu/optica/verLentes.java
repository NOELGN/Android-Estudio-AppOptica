package com.tesji.edu.optica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tesji.edu.optica.com.tesji.edu.opticamodelo.OpticaSQLite;

public class verLentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lentes);

        ListView list;
        list = (ListView) findViewById(R.id.listLentes);

        OpticaSQLite conexion = new OpticaSQLite(this, "agendaLentes.db", null, 1);

        SQLiteDatabase bd = conexion.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM lentes", null);
        int cont = fila.getCount();

        String array[] = new String[cont];
        fila.moveToFirst();

        int x=0;

        while(x<cont){
            array[x]="articulo: "+fila.getString(0)+"\nMarca: "+fila.getString(1)+"\nModelo: "+fila.getString(2)+"\nTipo Mica: "+fila.getString(3)
                     +"\nPrecio $"+fila.getString(4)+ "\nGenero: " +fila.getString(5)+ "\nEstilo: " +fila.getString(6);
            x++;
            fila.moveToNext();
        }

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        list.setAdapter(adaptador);
    }
}
