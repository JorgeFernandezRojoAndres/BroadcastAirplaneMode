package com.jorge.broadcastairplanemode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultados;
    private BroadcastReceiver updateReceiver;
    private AirplaneModeReceiver airplaneModeReceiver; // 🔹 Nuevo: receiver del sistema

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Vinculamos el TextView del layout
        txtResultados = findViewById(R.id.txtResultados);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mensaje inicial
        if (txtResultados != null) {
            txtResultados.setText("Esperando evento de Modo Avión...");
        }

        // 🔹 Receptor interno para escuchar actualizaciones desde AirplaneModeReceiver
        updateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String estado = intent.getStringExtra("estado");
                if (estado != null && txtResultados != null) {
                    txtResultados.setText(estado);
                }
            }
        };
        registerReceiver(updateReceiver, new IntentFilter("UPDATE_UI"), RECEIVER_NOT_EXPORTED);

        // 🔹 Registrar dinámicamente el AirplaneModeReceiver para Android 12+
        airplaneModeReceiver = new AirplaneModeReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeReceiver, filter, RECEIVER_NOT_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (updateReceiver != null) {
            unregisterReceiver(updateReceiver);
        }
        if (airplaneModeReceiver != null) {
            unregisterReceiver(airplaneModeReceiver);
        }
    }
}
