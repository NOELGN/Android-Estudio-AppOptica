package com.tesji.edu.optica;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    private final int DURACION = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        cargarSplash();

    }

    public void cargarSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent noa = new Intent(splash.this,principal.class);
                startActivity(noa);
            }
        },DURACION);
    }
}
