package com.tesji.edu.optica.com.tesji.edu.opticamodelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpticaSQLite extends SQLiteOpenHelper {

    public OpticaSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE lentes(idLente INTEGER PRIMARY KEY AUTOINCREMENT,marca TEXT NOT NULL,modelo TEXT UNIQUE NOT NULL,tipomica TEXT NOT NULL,precio TEXT NOT NULL,genero TEXT NOT NULL,tipolente TEXT NOT NULL)");
        db.execSQL("CREATE TABLE comprador(email TEXT PRIMARY KEY ,nombre TEXT NOT NULL,apellidos TEXT NOT NULL,telefono TEXT UNIQUE NOT NULL,fechacompra TEXT NOT NULL,gradod TEXT NOT NULL,gradoi text NOT NULL,idLente INTEGER NOT NULL,FOREIGN KEY(idLente)REFERENCES lentes(idLente))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
