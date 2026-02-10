#!/bin/bash
# ============================================================================
# IntentoDeApp - Compilar e Instalar Automáticamente (Linux/Mac)
# ============================================================================

echo ""
echo "╔════════════════════════════════════════════════════════════════╗"
echo "║          IntentoDeApp - Compilación Automática                ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Verificar que estamos en la carpeta correcta
if [ ! -f "app/build.gradle.kts" ]; then
    echo "❌ Error: Este script debe ejecutarse desde la carpeta IntentoDeApp"
    exit 1
fi

echo "📱 Preparando compilación..."
echo ""

# Limpiar builds anteriores
echo "🧹 Limpiando proyectos previos..."
./gradlew clean

echo ""
echo "🔨 Compilando aplicación..."
echo "⏳ Esto puede tardar 2-5 minutos (primera vez)..."
echo ""

# Compilar APK en debug
./gradlew assembleDebug

if [ $? -ne 0 ]; then
    echo ""
    echo "❌ Error en la compilación"
    echo "Por favor verifica:"
    echo "• Java 17 instalado: java -version"
    echo "• Android SDK actualizado"
    echo "• Gradle actualizado"
    exit 1
fi

echo ""
echo "✅ Compilación completada"
echo ""

# Encontrar el APK generado
APK_PATH="app/build/outputs/apk/debug/app-debug.apk"

if [ ! -f "$APK_PATH" ]; then
    echo "❌ No se encontró el APK generado"
    exit 1
fi

echo "📦 APK generado en: $APK_PATH"
echo ""

# Preguntar si quiere instalar
echo "¿Deseas instalar la app ahora?"
echo "(Asegúrate de tener un dispositivo conectado o emulador corriendo)"
echo ""
read -p "Escribe 's' para instalar o 'n' para salir: " INSTALL

if [ "$INSTALL" = "s" ] || [ "$INSTALL" = "S" ]; then
    echo ""
    echo "📱 Instalando en dispositivo..."
    adb install -r "$APK_PATH"
    
    if [ $? -ne 0 ]; then
        echo ""
        echo "⚠️ Error en la instalación"
        echo "Verifica que:"
        echo "• El dispositivo está conectado: adb devices"
        echo "• ADB está habilitado en el dispositivo"
        echo "• El emulador está corriendo"
        echo ""
        echo "El APK está en: $APK_PATH"
        echo "Puedes arrastrarlo manualmente en el emulador"
    else
        echo ""
        echo "✅ ¡App instalada exitosamente!"
        echo ""
        echo "Lanzando app..."
        adb shell am start -n com.intento/.MainActivity
        echo ""
        echo "🎉 ¡Listo! La app se abrirá en tu dispositivo"
    fi
else
    echo ""
    echo "ℹ️  APK listo en: $APK_PATH"
    echo "Puedes instalarlo manualmente arrastrándolo al emulador"
    echo "O usando: adb install -r \"$APK_PATH\""
fi

echo ""
