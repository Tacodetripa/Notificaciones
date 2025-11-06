package com.ejemplo.notificaciones.utils // ¡Tu paquete!

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

/**
 * 🎧 NotificationHelper - El Maestro de Ceremonias de tus Notificaciones
 *
 * Esta clase es un "object" (singleton) porque solo necesitamos una instancia
 * de ella para registrar los canales.
 */
object NotificationHelper {

    // Constantes - Como las "etiquetas" de tus cajas organizadoras 📦
    const val CHANNEL_ID = "estudio_recordatorios_channel"
    const val CHANNEL_NAME = "Recordatorios de Estudio"
    const val CHANNEL_DESCRIPTION = "Notificaciones motivadoras para no olvidar tus estudios 📚"
    const val NOTIFICATION_ID = 1001 // Un ID base para nuestras notificaciones

    /**
     * 📻 Crea el canal de notificaciones
     *
     * Piensa en esto como crear tu propia estación de radio.
     * Solo necesitas hacerlo UNA vez (Android 8.0+ lo requiere).
     *
     * @param context El contexto de tu app (como tu "dirección" dentro de Android)
     */
    fun createNotificationChannel(context: Context) {
        // Solo Android 8.0+ (API 26+) necesita canales
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Definimos la IMPORTANCIA - ¿Es una emergencia o un recordatorio gentil?
            // IMPORTANCE_HIGH hace que aparezca la notificación "heads-up" (flotante)
            val importance = NotificationManager.IMPORTANCE_HIGH

            // Creamos el canal con sus características
            val channel = NotificationChannel(
                CHANNEL_ID, // ID único - como tu número de teléfono
                CHANNEL_NAME, // Nombre visible - como tu nombre artístico
                importance // Qué tan "ruidoso" será
            ).apply {
                description = CHANNEL_DESCRIPTION // Descripción visible en Ajustes

                // Configuraciones adicionales - ¡el toque especial! ✨
                enableLights(true) // LED de notificación (si el dispositivo lo tiene)
                enableVibration(true) // Vibración - ¡bzz bzz! 📳
                setShowBadge(true) // Muestra el "numerito" en el ícono de la app
            }

            // Registramos nuestro canal en el sistema
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)

            // Un log para saber que todo salió bien
            println("✅ Canal de notificaciones creado exitosamente!")
        }
    }
}