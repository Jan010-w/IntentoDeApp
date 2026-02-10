@echo off
REM ============================================================================
REM SETUP AUTOMATICO - IntentoDeApp
REM ============================================================================
REM Este script lo configura TODO automáticamente
REM ============================================================================

color 0A
cls

echo.
echo ╔════════════════════════════════════════════════════════════════════════╗
echo ║                   INTENDODEAPP - SETUP AUTOMATICO                      ║
echo ║                 Instala TODA la app en 1 solo comando                  ║
echo ╚════════════════════════════════════════════════════════════════════════╝
echo.

REM Verificar prerrequisitos
echo 🔍 Verificando requisitos previos...
echo.

REM Verificar Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java 17 NO está instalado
    echo.
    echo Descarga desde: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
    echo.
    pause
    exit /b 1
)
echo ✅ Java instalado

REM Verificar Android SDK
if exist "%LOCALAPPDATA%\Android\Sdk\platforms\android-34" (
    echo ✅ Android SDK 34 instalado
) else (
    echo ❌ Android SDK 34 NO está instalado
    echo.
    echo Abre Android Studio:
    echo 1. Tools → SDK Manager
    echo 2. Descarga API 34
    echo.
    pause
    exit /b 1
)

REM Verificar ADB
adb --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ADB no está en el PATH
    echo.
    pause
    exit /b 1
)
echo ✅ ADB disponible

echo.
echo ✅ Todos los requisitos están OK
echo.

REM Crear archivos locales si no existen
if not exist "local.properties" (
    echo 📝 Creando archivo de configuración...
    (
        echo # Configuración local de IntentoDeApp
        echo sdk.dir=%LOCALAPPDATA%\Android\Sdk
    ) > local.properties
    echo ✅ local.properties creado
) else (
    echo ✅ Configuración local ya existe
)

echo.
echo 📦 Compilando aplicación...
echo ⏳ Esto puede tardar 3-5 minutos en la primera compilación...
echo.

REM Limpiar y compilar
call gradlew.bat clean assembleDebug

if %errorlevel% neq 0 (
    echo.
    echo ❌ Error en la compilación
    echo.
    echo Por favor verifica:
    echo • Java 17 está correctamente instalado
    echo • Android SDK está actualizado
    echo • Hay al menos 5GB libres en disco
    echo.
    pause
    exit /b 1
)

echo.
echo ✅ ¡Compilación exitosa!
echo.

REM Buscar APK
set APK_PATH=app\build\outputs\apk\debug\app-debug.apk

if not exist "%APK_PATH%" (
    echo ❌ No se encontró el APK
    pause
    exit /b 1
)

echo 📦 APK creado: %APK_PATH%
echo.

REM Instalar en dispositivo
echo 📱 Instalando en dispositivo...
echo.
echo ⚠️  Asegúrate de que:
echo   • Un dispositivo Android está conectado
echo   • O un emulador está corriendo
echo.
echo Presiona ENTER para continuar...
pause

adb devices
echo.

adb install -r "%APK_PATH%"

if %errorlevel% neq 0 (
    echo.
    echo ⚠️ No se pudo instalar automáticamente
    echo.
    echo Alternativas:
    echo 1. Conecta el dispositivo y abre el emulador
    echo 2. Ejecuta: adb install -r "%APK_PATH%"
    echo 3. O arrastra el APK al emulador
    echo.
    pause
    exit /b 1
)

echo.
echo ✅ ¡App instalada exitosamente!
echo.

REM Lanzar app
echo 🚀 Lanzando aplicación...
adb shell am start -n com.intento/.MainActivity

echo.
echo ╔════════════════════════════════════════════════════════════════════════╗
echo ║                      ✅ ¡LISTO! APP EN MARCHA                         ║
echo ║                                                                        ║
echo ║  Tu app IntentoDeApp se abrirá ahora en el dispositivo                ║
echo ║                                                                        ║
echo ║  Ahora puedes:                                                         ║
echo ║  • Crear tareas                                                        ║
echo ║  • Establecer prioridades                                              ║
echo ║  • Marcar como completadas                                             ║
echo ║  • Ver sugerencias de IA                                               ║
echo ║                                                                        ║
echo ║  Documentación: Lee archivos .md en la carpeta                        ║
echo ║                                                                        ║
echo ╚════════════════════════════════════════════════════════════════════════╝
echo.

pause
