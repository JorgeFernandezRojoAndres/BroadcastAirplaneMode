# 📱 BroadcastReceiver - Modo Avión

## 🎯 Objetivo
Aplicar el uso de **BroadcastReceiver** en Android para capturar eventos del sistema y responder a ellos mediante **Intents implícitos**.

---

## 📌 Consigna implementada
1. La aplicación implementa un **BroadcastReceiver** (`AirplaneModeReceiver`) que escucha el evento del sistema:
android.intent.action.AIRPLANE_MODE_CHANGED
2. Al detectar que el **Modo Avión** se activa:
- Se muestra un **Toast** con el mensaje ✈️.
- Se abre la aplicación de llamadas con el número **2664553747** usando un `Intent` implícito.
3. Al desactivarse el Modo Avión:
- Se muestra un **Toast** con el mensaje 📶.

---

## 🚀 Cómo probar la app
1. Instalar la aplicación en un **dispositivo físico** o en un **emulador oficial (AVD)** de Android Studio.
2. Activar/desactivar el **Modo Avión** desde la barra de notificaciones del dispositivo.
3. Resultados esperados:
- Al **activar**: se abre la app de llamadas con `2664553747`.
- Al **desactivar**: se muestra un Toast indicando “Modo avión desactivado”.

⚠️ Nota: En **BlueStacks**, el botón de Modo Avión **no dispara el broadcast real**.  
En este caso, se puede simular con ADB:
```bash
adb shell settings put global airplane_mode_on 1
adb shell am broadcast -a android.intent.action.AIRPLANE_MODE_CHANGED --ez state true

adb shell settings put global airplane_mode_on 0
adb shell am broadcast -a android.intent.action.AIRPLANE_MODE_CHANGED --ez state false
📂 Estructura principal

MainActivity.java → Pantalla principal con un TextView.

AirplaneModeReceiver.java → BroadcastReceiver que escucha el cambio de Modo Avión.

AndroidManifest.xml → Registro del BroadcastReceiver con android:exported="true".
✍️ Autor

Jorge Fernández Rojo Andrés
