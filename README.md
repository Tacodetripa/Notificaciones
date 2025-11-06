# ✨ CODE NEON - NOTIFY | Sistema de Estímulo Motivacional

[![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/studio)

---

## 🚀 Descripción del Proyecto

**CODE NEON - NOTIFY** es una aplicación de demostración de Android enfocada en la implementación robusta de notificaciones del sistema con un canal dedicado y la gestión de permisos (`POST_NOTIFICATIONS` en Android 13+).

El proyecto se presenta con un **diseño de alto contraste estilo Neón** implementado completamente con **Jetpack Compose** y siguiendo las guías de Material 3, ofreciendo una experiencia visual única y moderna.

### 🎯 Características Principales

* **Gestión de Permisos:** Lógica completa para solicitar el permiso `POST_NOTIFICATIONS` requerido en Android Tiramisu (API 33) o superior.

* **Canal de Notificaciones:** Creación y registro de un canal de notificaciones dedicado (`motivational_channel`) para la segmentación.

* **Notificaciones Motivacionales:** Envío de mensajes de estímulo aleatorios predefinidos.

* **UI de Alto Contraste:** Interfaz de usuario con tema personalizado (`NotificationAppTheme`) que utiliza colores Neón (Verde) sobre un fondo oscuro (Azul Profundo).

## 🎨 Diseño y Tecnología

El diseño de este proyecto utiliza una paleta de alto contraste para un *look & feel* **Neón/Digital**.

| Elemento | Color | Hex Code | Uso Principal | 
 | :--- | :--- | :--- | :--- | 
| **Fondo** | Azul Oscuro | `#0D1B2A` | `background` y `surface` | 
| **Primario** | Verde Neón | `#39FF14` | Botones, títulos y acentos (`primary`) | 
| **Secundario** | Cian Brillante | `#00FFFF` | Elementos de apoyo (`secondary`) | 
| **Contraste** | Blanco Puro | `#FFFFFF` | Texto principal sobre fondo oscuro | 

**Tecnologías Utilizadas:**

* **Lenguaje:** Kotlin

* **UI Frameworks:** Jetpack Compose

* **Versión Mínima SDK:** 24 (Android 7.0 - Nougat)

* **Target SDK:** 34 (Android 14)
