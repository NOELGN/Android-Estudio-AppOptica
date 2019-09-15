package com.tesji.edu.optica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tesji.edu.optica.com.tesji.edu.opticamodelo.OpticaSQLite;

public class LentesVendidos extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lentes_vendidos);

        list = (ListView)findViewById(R.id.listVentas);

        OpticaSQLite conexion = new OpticaSQLite(
                this,
                "agendaLentes.db",
                null,1);

        SQLiteDatabase bd = conexion.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT c.marca || ' ' || c.modelo,c.tipomica || 'Graduacion: D ' ||a.gradod|| ' I ' || a.gradoi ,c.tipolente ,c.genero ,a.fechacompra, a.nombre || ' ' || a.apellidos, a.telefono,c.precio FROM comprador AS a INNER JOIN lentes AS c ON a.idLente = c.idLente",null);
        int cont = fila.getCount();

        String array[] = new String[cont];
        fila.moveToFirst();

        int x=0;

        while (x<cont){
            array[x] = "Lentes "+fila.getString(0)+
                    "\nCon micas: "+fila.getString(1)+
                    "\nEstilo: "+fila.getString(2)+
                    "\nPara: "+fila.getString(3)+
                    "\nEl dia $"+fila.getString(4)+
                    "\nCliente "+fila.getString(5)+
                    "\nSu telefono es: "+fila.getString(6)+
                    "\ncosto $"+fila.getString(7);
            x++;
            fila.moveToNext();
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        list.setAdapter(adaptador);
        bd.close();
    }
}
