package com.ejemplo.notificaciones

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.ejemplo.notificaciones.utils.AppNotificationManager
import com.ejemplo.notificaciones.utils.NotificationHelper

// Definición de la fuente y paleta de colores para una apariencia "Neón"
// Estos valores de Color se usarán en el tema.
val NeonGreen = Color(0xFF39FF14) // Color principal
val DarkBlue = Color(0xFF0D1B2A) // Color de fondo
val LightGray = Color(0xFFE0E0E0) // Color de texto secundario
val CyanLight = Color(0xFF00FFFF) // Color terciario

class MainActivity : ComponentActivity() {

    // 1. 👮‍♂️ Nuestro "portero" para pedir permisos
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            println("✅ ¡Permiso concedido! Ahora podemos notificar")
        } else {
            println("❌ Permiso denegado. El usuario no quiere notificaciones")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. 📻 Crear el canal de notificaciones (solo se hace una vez)
        NotificationHelper.createNotificationChannel(this)

        // 3. 🚦 Pedir permiso si es necesario
        checkAndRequestNotificationPermission()

        // 4. 🎨 Configurar la interfaz con Jetpack Compose
        setContent {
            // Usamos el tema personalizado
            NotificationAppTheme {
                // El Surface ahora usa el fondo oscuro
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Esta es nuestra pantalla principal
                    NotificationScreen()
                }
            }
        }
    }

    /**
     * Verifica y solicita el permiso de notificaciones (en Android 13+)
     */
    private fun checkAndRequestNotificationPermission() {
        // Solo para Android 13 (TIRAMISU) o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                // ¿Ya tenemos permiso?
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    println("✅ Ya tenemos permiso para notificar")
                }
                // Pedir permiso al usuario
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    // --- Esta es la UI ---

    /**
     * 📱 Pantalla principal de la app con estilo Neón
     */
    @Composable
    fun NotificationScreen() {
        // 🧠 Estado para contar notificaciones (remember guarda el valor)
        var notificationCount by remember { mutableStateOf(0) }

        // 🧠 Instancia de nuestro gestor de notificaciones
        val notificationManager = remember { AppNotificationManager(this) }

        // 🎨 Contenedor principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), // Espaciado interior
            horizontalAlignment = Alignment.CenterHorizontally, // Centrar todo
            verticalArrangement = Arrangement.Center // Centrar verticalmente
        ) {
            // Título
            Text(
                text = "✨ CODE NEON - NOTIFY ✨",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary, // Verde Neón
                style = MaterialTheme.typography.headlineLarge, // Utiliza tipografía más grande
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Descripción
            Text(
                text = "Tu sistema de motivación digital para impulsar tu productividad.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = LightGray, // Gris claro
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(64.dp))

            // 🔘 ¡EL BOTÓN MÁGICO!
            Button(
                onClick = {
                    // 1. Obtener mensaje aleatorio
                    val (title, message) = notificationManager.getRandomMotivationalMessage()

                    // 2. Enviar notificación
                    notificationManager.sendMotivationalNotification(
                        title = title,
                        message = message
                    )

                    // 3. Incrementar contador
                    notificationCount++
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp), // Botón más alto
                shape = RoundedCornerShape(12.dp), // Bordes más suaves
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Verde Neón
                    contentColor = DarkBlue // Texto Azul Oscuro
                )
            ) {
                Text(
                    text = "► GENERAR ESTÍMULO MOTIVACIONAL",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 📊 Contador de notificaciones (Estilo de display digital)
            Card(
                modifier = Modifier.fillMaxWidth(0.8f), // Más compacto
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E3A52), // Azul intermedio para contraste
                    contentColor = LightGray
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "NOTIFICACIONES ENVIADAS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LightGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$notificationCount",
                        fontSize = 56.sp, // Tamaño grande para el contador
                        fontWeight = FontWeight.ExtraBold,
                        color = NeonGreen // Contador en color Neón
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // 💡 Consejo adicional (Card de Terciario)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A5A5A), // Tono cian oscuro para terciario
                    contentColor = CyanLight // Texto en cian claro
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Sombra
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚡",
                        fontSize = 32.sp, // Tamaño del emoji
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Text(
                        text = "Aviso: Necesitarás aceptar el permiso de notificaciones de Android (POST_NOTIFICATIONS) la primera vez que inicies la app.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

/**
 * 🎨 Tema de la aplicación: NEON HIGH CONTRAST
 *
 * Se utiliza una paleta oscura con un color de acento brillante.
 */
@Composable
fun NotificationAppTheme(content: @Composable () -> Unit) {
    // Definimos una paleta de colores de Material 3 basada en alto contraste
    val colorScheme = lightColorScheme(
        // Colores principales de la marca (Neón)
        primary = NeonGreen, // Verde neón brillante (Para botones, textos destacados)
        onPrimary = DarkBlue, // Texto en el botón primario

        // Colores secundarios y terciarios
        secondary = CyanLight, // Cian brillante
        tertiary = CyanLight, // También usamos cian como terciario

        // Colores de fondo y superficie (Oscuros)
        background = DarkBlue, // Azul oscuro profundo
        surface = DarkBlue, // Superficie oscura
        onBackground = Color.White, // Texto sobre el fondo
        onSurface = Color.White, // Texto sobre la superficie

        // Contenedores y texto sobre contenedores
        // Usamos LightGray (blanco roto) para texto secundario
        onSurfaceVariant = LightGray,

        // Contenedor para la Card del contador
        secondaryContainer = Color(0xFF1E3A52),
        onSecondaryContainer = LightGray,

        // Contenedor para la Card de consejos
        tertiaryContainer = Color(0xFF1A5A5A),
        onTertiaryContainer = CyanLight
    )

    MaterialTheme(
        colorScheme = colorScheme,
        // Usamos la fuente por defecto, pero se podrían definir fuentes específicas aquí
        content = content
    )
}