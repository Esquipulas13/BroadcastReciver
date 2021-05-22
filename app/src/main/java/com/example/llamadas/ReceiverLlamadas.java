package com.example.llamadas;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceiverLlamadas extends BroadcastReceiver {

    class miPhoneStateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);

            if ((state == TelephonyManager.CALL_STATE_RINGING) && (phoneNumber.equals(MainActivity.numero)))
                mandarMensaje(phoneNumber);

            Log.println(Log.INFO,"accion","Mandar mensaje a " + phoneNumber);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager =(TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
        telephonyManager.listen(new miPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);

        Log.println(Log.INFO,"llamando","Llamando " + MainActivity.numero);
    }


    public void mandarMensaje(String numero){
        SmsManager sms =SmsManager.getDefault();
        String mensaje = "probando si puedo mandar mensajes";
        sms.sendTextMessage(numero, null,MainActivity.mensaje, null, null);


    }
}
