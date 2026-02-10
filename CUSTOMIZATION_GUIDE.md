# Guía de Personalización - IntentoDeApp

## 🎨 Temas

### Cambiar Colores Primarios

En [Theme.kt](app/src/main/java/com/intento/ui/theme/Theme.kt):

```kotlin
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6C63FF),      // Cambiar aquí
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    error = Color(0xFFCF6679)
)
```

### Agregar Nuevo Tema

```kotlin
private val CustomColorScheme = darkColorScheme(
    primary = Color(0xFFFF5722),      // Tu color
    secondary = Color(0xFFFF9800),
    // ... más colores
)

// En IntentoTheme()
val colorScheme = when {
    theme == "custom" -> CustomColorScheme
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
}
```

## 🏷️ Categorías Personalizadas

### Agregar Nueva Categoría

En [Models.kt](app/src/main/java/com/intento/domain/Models.kt):

```kotlin
enum class TaskCategory(val displayName: String, val icon: String, val color: String) {
    WORK("Trabajo", "briefcase", "#FF5722"),
    PERSONAL("Personal", "person", "#2196F3"),
    SHOPPING("Compras", "shopping_cart", "#4CAF50"),
    HEALTH("Salud", "favorite", "#E91E63"),
    CUSTOM("Personalizada", "tag", "#9C27B0")
}
```

## 🎯 Prioridades Personalizadas

Cambiar en [Models.kt](app/src/main/java/com/intento/domain/Models.kt):

```kotlin
enum class Priority(val level: Int, val icon: String, val color: String) {
    LOW(1, "arrow_downward", "#4CAF50"),
    MEDIUM(2, "remove", "#FF9800"),
    HIGH(3, "arrow_upward", "#F44336"),
    URGENT(4, "priority_high", "#9C27B0")
}
```

## 🔔 Personalizar Notificaciones

En [NotificationService.kt](app/src/main/java/com/intento/service/NotificationService.kt):

```kotlin
// Cambiar sonido de notificación
.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

// Cambiar vibración
.setVibrate(longArrayOf(0, 200, 200, 200))

// Cambiar LED
.setLights(0xFF00FF00.toInt(), 300, 300)

// Cambiar prioridad
.setPriority(NotificationCompat.PRIORITY_MAX)
```

## 🖼️ Cambiar Iconos

Coloca tus iconos en `app/src/main/res/drawable/` y referencia:

```kotlin
Icon(
    painter = painterResource(id = R.drawable.ic_custom_icon),
    contentDescription = "Mi icono"
)
```

## ⏰ Personalizar Recordatorios

En [TaskViewModel.kt](app/src/main/java/com/intento/viewmodel/TaskViewModel.kt):

```kotlin
fun scheduleReminder(taskId: Long, minutesFromNow: Long) {
    // Modificar lógica de recordatorios
    val reminderTime = LocalDateTime.now().plusMinutes(minutesFromNow)
}
```

## 🤖 Personalizar Agente IA

En [AIAgent.kt](app/src/main/java/com/intento/service/AIAgent.kt):

```kotlin
fun generateSuggestions(): List<AISuggestion> {
    // Modificar sugerencias según tus necesidades
    return listOf(
        AISuggestion(
            title = "Mi sugerencia",
            description = "Tu descripción personalizada"
        )
    )
}
```

## 📐 Cambiar Dimensiones

Crea `app/src/main/res/values/dimens.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="padding_small">8dp</dimen>
    <dimen name="padding_medium">16dp</dimen>
    <dimen name="padding_large">24dp</dimen>
    <dimen name="text_size_small">12sp</dimen>
    <dimen name="text_size_medium">16sp</dimen>
    <dimen name="text_size_large">20sp</dimen>
</resources>
```

Uso en Compose:

```kotlin
modifier = Modifier.padding(
    horizontal = dimensionResource(R.dimen.padding_medium)
)
```

## 🌐 Cambiar Idioma

Crea `app/src/main/res/values-es/strings.xml`:

```xml
<resources>
    <string name="app_name">IntentoDeApp</string>
    <string name="add_task">Agregar Tarea</string>
    <!-- ... más strings en español -->
</resources>
```

## 📱 Ajustar Layout para Tablets

Crea `app/src/main/res/values-sw600dp/dimens.xml`:

```xml
<resources>
    <dimen name="padding_small">12dp</dimen>
    <dimen name="padding_medium">24dp</dimen>
    <dimen name="padding_large">36dp</dimen>
</resources>
```

## 🎬 Agregar Animaciones

En tus Composables:

```kotlin
@Composable
fun AnimatedTaskItem() {
    val animationSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMedium
    )
    
    val offset by animateFloatAsState(
        targetValue = if (isVisible) 0f else -100f,
        animationSpec = animationSpec
    )
    
    Box(modifier = Modifier.offset(x = offset.dp)) {
        // Tu contenido
    }
}
```

## 🔧 Configuración Avanzada

### Firebase Configuration

Crea `app/google-services.json` con tus credenciales de Firebase.

### Backend URL

En `AppModule.kt`:

```kotlin
.baseUrl(BuildConfig.BACKEND_URL)
```

Luego en `build.gradle.kts`:

```kotlin
buildTypes {
    debug {
        buildConfigField("String", "BACKEND_URL", "\"http://localhost:3000/\"")
    }
    release {
        buildConfigField("String", "BACKEND_URL", "\"https://api.intento.com/\"")
    }
}
```

## 🎯 Tips de Personalización

1. **No modificar** archivos generados automáticamente
2. **Usar** valores de color en `colors.xml`
3. **Crear** temas basados en Material Design 3
4. **Probar** en múltiples tamaños de pantalla
5. **Documentar** tus cambios en comentarios

## 📦 Paquetes Sugeridos para Personalización

```gradle
// Fondos personalizados
implementation("com.airbnb.android:lottie:6.1.0")

// Animaciones avanzadas
implementation("androidx.compose.foundation:foundation-android:1.5.0")

// Temas dinámicos
implementation("androidx.dynamiccolor:dynamiccolor:1.0.0")

// WebView para contenido HTML
implementation("androidx.webkit:webkit:1.7.0")
```

---

**Recuerda**: Personaliza gradualmente y prueba después de cada cambio.

¿Necesitas ayuda con una personalización específica? 🎨
