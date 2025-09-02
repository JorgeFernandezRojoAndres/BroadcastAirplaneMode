package com.jorge.broadcastairplanemode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

            if (isAirplaneModeOn) {
                Toast.makeText(context, "✈️ Modo avión activado", Toast.LENGTH_SHORT).show();

                // 🔹 Enviar mensaje a MainActivity
                Intent update = new Intent("UPDATE_UI");
                update.putExtra("estado", "✈️ Modo Avión ACTIVADO");
                context.sendBroadcast(update);

                // 🔹 Abrir app de llamadas (Intent implícito)
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:2664553747"));
                callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // ✅ Verificar que haya app de teléfono disponible
                if (callIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(callIntent);
                } else {
                    Toast.makeText(context, "⚠️ No se encontró aplicación de teléfono", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(context, "📶 Modo avión desactivado", Toast.LENGTH_SHORT).show();

                // 🔹 Enviar mensaje a MainActivity
                Intent update = new Intent("UPDATE_UI");
                update.putExtra("estado", "📶 Modo Avión DESACTIVADO");
                context.sendBroadcast(update);
            }
        }
    }
}
