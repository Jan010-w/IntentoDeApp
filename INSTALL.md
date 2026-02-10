# Guía de Instalación - IntentoDeApp

## Requisitos Previos

1. **Android Studio** 2023.1.1 o superior
2. **Java Development Kit (JDK)** 17 o superior
3. **Android SDK** API 34 (compileSdk)
4. **Gradle** 8.2 o superior

## Pasos de Instalación

### 1. Clonar o Descargar el Proyecto

```bash
cd /ruta/a/tu/workspace
# Si usas git:
# git clone <repository-url>
```

### 2. Abrir en Android Studio

1. Abre Android Studio
2. Selecciona "Open" → Navega a la carpeta `IntentoDeApp`
3. Espera a que Gradle sincronice las dependencias

### 3. Configuración Inicial

```bash
# Desde la terminal en Android Studio o tu terminal preferida
./gradlew clean build

# En Windows:
gradlew.bat clean build
```

### 4. Conectar Dispositivo o Emulador

**Opción A: Dispositivo físico**
- Conecta tu dispositivo Android via USB
- Habilita "Depuración de USB" en Configuración > Opciones de Desarrollador
- Ejecuta: `adb devices` para verificar que se reconoce

**Opción B: Emulador**
1. Abre Android Studio → AVD Manager
2. Crea o selecciona un emulador virtual
3. Inicia el emulador

### 5. Ejecutar la Aplicación

```bash
# Opción 1: Desde Android Studio
- Click derecho en "app" → Run "app"
- O presiona Shift + F10

# Opción 2: Desde terminal
./gradlew installDebug
adb shell am start -n com.intento/.MainActivity
```

## Solución de Problemas

### Error: "SDK location not found"
```bash
# Crea un archivo local.properties en la raíz del proyecto:
echo "sdk.dir=/ruta/al/android/sdk" > local.properties
```

### Error: "Gradle sync failed"
```bash
# Ejecuta:
./gradlew clean
./gradlew sync

# Si persiste, invalida el caché:
File → Invalidate Caches → Restart
```

### Error: "Hilt compilation failed"
```bash
# Asegúrate de tener kapt configurado:
# Ya está en build.gradle.kts
# Si no funciona:
./gradlew clean build --stacktrace
```

### Error: "Could not find androidx.room:room-compiler"
```bash
# Actualiza las dependencias:
./gradlew --refresh-dependencies
```

## Variables de Entorno (Opcional)

```bash
# En Windows (PowerShell):
$env:ANDROID_SDK_ROOT = "C:\Users\YourUsername\AppData\Local\Android\Sdk"

# En Linux/Mac:
export ANDROID_SDK_ROOT=$HOME/Android/Sdk
export ANDROID_HOME=$ANDROID_SDK_ROOT
```

## Generar APK para Distribución

```bash
# Debug APK:
./gradlew assembleDebug

# Release APK (requiere keystore):
./gradlew assembleRelease

# Los APKs estarán en: app/build/outputs/apk/
```

## Configurar Keystore para Release

```bash
# Generar keystore:
keytool -genkey -v -keystore release.keystore -keyalg RSA -keysize 2048 -validity 10000

# Luego configura en build.gradle.kts
```

## Características de Desarrollo

### Habilitar Modo Debug
```kotlin
// En IntentoApplication.kt
BuildConfig.DEBUG // Ya disponible automáticamente
```

### Logs
```kotlin
import android.util.Log

Log.d("TAG", "Mensaje")
Log.e("TAG", "Error", throwable)
```

### Testing
```bash
./gradlew test              # Unit tests
./gradlew connectedAndroidTest  # Instrumented tests
```

## Actualizar Dependencias

```bash
./gradlew dependencyUpdates

# O manualmente en build.gradle.kts
```

## Información de Versión

- **compileSdk**: 34
- **minSdk**: 24
- **targetSdk**: 34
- **Kotlin**: 1.9.10
- **Compose**: 2023.10.01

## Verificar Instalación

```bash
# Verificar que la app está instalada:
adb shell pm list packages | grep com.intento

# Ver logs de la app:
adb logcat | grep com.intento
```

## Siguientes Pasos

1. ✅ Ejecutar la app en tu dispositivo
2. 📝 Crear tu primera tarea
3. 🔔 Configurar recordatorios
4. 🤖 Activar el agente IA
5. 📊 Ver estadísticas

¡Listo! 🎉 Tu app debería estar corriendo.

---

**¿Tienes problemas?** Comprueba:
- JDK versión correcta: `java -version`
- SDK actualizado: Android Studio → Tools → SDK Manager
- Gradle limpio: `./gradlew clean`
