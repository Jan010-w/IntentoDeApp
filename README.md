# IntentoDeApp - Tu Asistente de Tareas con IA

Una aplicación Android completa para gestionar tareas, recordatorios y listas con asistencia de inteligencia artificial.

## 🎯 Características

### Gestión de Tareas
- ✅ Crear, editar y eliminar tareas
- 🏷️ Categorizar tareas
- 🎯 Establecer prioridades (Baja, Media, Alta, Urgente)
- 📅 Fechas de vencimiento
- ✔️ Marcar tareas como completadas
- 🏷️ Etiquetas personalizables

### Recordatorios
- 🔔 Notificaciones automáticas
- ⏰ Recordatorios programados
- 🤖 Recordatorios inteligentes basados en IA

### Agente IA
- 💡 Sugerencias automáticas de tareas
- 📊 Análisis de productividad
- 🎯 Optimización de prioridades
- 📈 Estadísticas y reportes

### Listas
- 📝 Crear múltiples listas
- 🎨 Personalizar colores e iconos
- 👥 Compartir listas (próxima versión)

## 🏗️ Arquitectura

```
Kotlin + Jetpack Compose (UI)
        ↓
ViewModel (Lógica)
        ↓
Repository (Datos)
        ↓
Room Database (Local)
WorkManager (Notificaciones)
        ↓
```

### Capas:
- **UI**: Jetpack Compose
- **ViewModel**: Estado de la aplicación
- **Repository**: Acceso a datos
- **Database**: Room (SQLite)
- **Service**: Notificaciones y IA
- **DI**: Hilt (Inyección de dependencias)

## 🔧 Stack Tecnológico

- **Lenguaje**: Kotlin
- **UI**: Jetpack Compose
- **Base de datos**: Room + SQLite
- **Inyección**: Hilt
- **Tareas programadas**: WorkManager
- **API REST**: Retrofit + OkHttp
- **Notificaciones**: Firebase Cloud Messaging (opcional)

## 📦 Dependencias Principales

```gradle
- androidx.compose:compose-bom:2023.10.01
- androidx.room:room-runtime:2.6.1
- com.google.dagger:hilt-android:2.47
- androidx.work:work-runtime-ktx:2.8.1
- com.squareup.retrofit2:retrofit:2.9.0
```

## 🚀 Próximos Pasos

1. **Configurar Firebase** para notificaciones push
2. **Integrar OpenAI API** para mejores sugerencias de IA
3. **Implementar sincronización en la nube**
4. **Agregar widget de inicio**
5. **Soporte para compartir listas**
6. **Análisis de patrones de productividad**
7. **Voz para crear tareas**

## 📋 Estructura de Carpetas

```
IntentoDeApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/intento/
│   │   │   ├── ui/              # Composables
│   │   │   ├── data/            # Room, Entities, DAOs
│   │   │   ├── domain/          # Models
│   │   │   ├── repository/      # Repository pattern
│   │   │   ├── viewmodel/       # ViewModels
│   │   │   ├── service/         # Servicios (IA, Notificaciones)
│   │   │   ├── MainActivity.kt
│   │   │   ├── IntentoApplication.kt
│   │   │   └── AppModule.kt
│   │   └── res/
│   │       ├── values/
│   │       └── drawable/
│   └── build.gradle.kts
├── backend/                      # Node.js (opcional para IA)
├── settings.gradle.kts
└── build.gradle.kts
```

## 🔐 Permisos Necesarios

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
```

## 💾 Uso de la Base de Datos

### Tablas:
- **tasks**: Almacena todas las tareas
- **task_lists**: Listas personalizadas
- **reminders**: Recordatorios

## 🤖 Sistema de IA

El agente IA puede:
1. Sugerir nuevas tareas basadas en patrones
2. Optimizar prioridades automáticamente
3. Analizar productividad
4. Generar reportes

## 🎨 Personalización

La app es completamente customizable:
- Temas claro/oscuro
- Colores personalizados para listas
- Iconos variados
- Notificaciones ajustables

## 📱 Requisitos

- Android 7.0+ (API 24)
- Kotlin 1.9.10+
- Gradle 8.2+

## 📝 Licencia

MIT License - Libre para usar y modificar

---

**¿Necesitas ayuda?** Pregúntame sobre cualquier aspecto de la app.
