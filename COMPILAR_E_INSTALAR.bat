@echo off
REM ============================================================================
REM IntentoDeApp - Compilar e Instalar Automáticamente
REM ============================================================================
REM Este script compila la app y la instala en tu dispositivo/emulador
REM ============================================================================

echo.
echo ╔════════════════════════════════════════════════════════════════╗
echo ║          IntentoDeApp - Compilación Automática                ║
echo ╚════════════════════════════════════════════════════════════════╝
echo.

REM Verificar que estamos en la carpeta correcta
if not exist "app\build.gradle.kts" (
    echo ❌ Error: Este script debe ejecutarse desde la carpeta IntentoDeApp
    pause
    exit /b 1
)

REM Verificar que gradle existe
if not exist "gradlew.bat" (
    echo ⚠️ Advertencia: gradlew.bat no encontrado
    echo Intentando usar gradle del sistema...
)

echo 📱 Preparando compilación...
echo.

REM Limpiar builds anteriores
echo 🧹 Limpiando proyectos previos...
call gradlew.bat clean

echo.
echo 🔨 Compilando aplicación...
echo ⏳ Esto puede tardar 2-5 minutos (primera vez)...
echo.

REM Compilar APK en debug
call gradlew.bat assembleDebug

if %errorlevel% neq 0 (
    echo.
    echo ❌ Error en la compilación
    echo Por favor verifica:
    echo • Java 17 instalado: java -version
    echo • Android SDK actualizado
    echo • Gradle actualizado
    pause
    exit /b 1
)

echo.
echo ✅ Compilación completada
echo.

REM Encontrar el APK generado
set APK_PATH=app\build\outputs\apk\debug\app-debug.apk

if not exist "%APK_PATH%" (
    echo ❌ No se encontró el APK generado
    pause
    exit /b 1
)

echo 📦 APK generado en: %APK_PATH%
echo.

REM Preguntar si quiere instalar
echo ¿Deseas instalar la app ahora?
echo (Asegúrate de tener un dispositivo conectado o emulador corriendo)
echo.
set /p INSTALL="Escribe 's' para instalar o 'n' para salir: "

if /i "%INSTALL%"=="s" (
    echo.
    echo 📱 Instalando en dispositivo...
    adb install -r "%APK_PATH%"
    
    if %errorlevel% neq 0 (
        echo.
        echo ⚠️ Error en la instalación
        echo Verifica que:
        echo • El dispositivo está conectado: adb devices
        echo • ADB está habilitado en el dispositivo
        echo • El emulador está corriendo
        echo.
        echo El APK está en: %APK_PATH%
        echo Puedes arrastrarlo manualmente en el emulador
    ) else (
        echo.
        echo ✅ ¡App instalada exitosamente!
        echo.
        echo Lanzando app...
        adb shell am start -n com.intento/.MainActivity
        echo.
        echo 🎉 ¡Listo! La app se abrirá en tu dispositivo
    )
) else (
    echo.
    echo ℹ️  APK listo en: %APK_PATH%
    echo Puedes instalarlo manualmente arrastrándolo al emulador
    echo O usando: adb install -r "%APK_PATH%"
)

echo.
pause
