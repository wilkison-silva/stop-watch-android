package com.will.stopwatch.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.will.stopwatch.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button buttonStop;
    private TextView textViewCronometro;
    long millisegundos = 0;
    boolean rodando = false;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.button_start);
        buttonStop = findViewById(R.id.button_stop);
        textViewCronometro = findViewById(R.id.textview_cronometro);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

         handler= new Handler();
         runnable = new Runnable() {
             @Override
             public void run() {
                 long hora = (long) (millisegundos / 3600);
                 long minuto = (long) ((millisegundos % 3600) / 60);
                 long segundo = (long) (millisegundos % 60);

                 String tempoDecorrido = String.format(Locale.getDefault(),
                         "%02d:%02d:%02d", hora,
                         minuto, segundo);

                 textViewCronometro.setText(tempoDecorrido);

                 if(rodando){
                     millisegundos = millisegundos + 1;
                 }

                 handler.postDelayed(this, 1);
             }
         };
    }

    private void stop(){
        rodando = false;
        handler.removeCallbacks(runnable);
    }

    private void start(){
        rodando = true;
        handler.post(runnable);
    }
}