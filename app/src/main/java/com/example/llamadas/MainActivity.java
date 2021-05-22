package com.example.llamadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.llamadas.ReceiverLlamadas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtNumero;
    EditText txtmensaje;
    TextView lblnumeroRegistrado;
    Button btnAceptar;
    Button enviar;
    public static String numero;
    public static String mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enviar = (Button)findViewById(R.id.button3);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMensaje(numero, mensaje);
            }
        });

        txtNumero = findViewById(R.id.editText);
        txtmensaje = findViewById(R.id.mensaje);
        lblnumeroRegistrado = findViewById(R.id.numeroRegistrado);
        btnAceptar = findViewById(R.id.button);

        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){ActivityCompat.requestPermissions(MainActivity.this,new String[]
                {
                Manifest.permission.SEND_SMS,
                },1000);
    }else{

    }

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    numero = txtNumero.getText().toString();
                    lblnumeroRegistrado.setText(numero);
                    mensaje = txtmensaje.getText().toString();
            }
        });

    }

    private void enviarMensaje (String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
