# ⚡ IntentoDeApp - INSTALACIÓN INMEDIATA

## 🚀 Instalación en 30 Segundos

### Windows
```bash
1. Abre PowerShell/CMD aquí
2. Ejecuta:
   .\SETUP.bat
3. ¡LISTO! App funcionando
```

### Mac/Linux
```bash
1. Abre terminal aquí
2. Ejecuta:
   chmod +x compilar_e_instalar.sh
   ./compilar_e_instalar.sh
3. ¡LISTO! App funcionando
```

---

## ✅ Requisitos Previos (Solo Estos)

- ✅ **Java 17+** (descargar: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- ✅ **Android Studio** (descargar: https://developer.android.com/studio)
- ✅ **Android SDK 34** (en Android Studio: Tools → SDK Manager → Descarga API 34)
- ✅ **Emulador o dispositivo Android conectado**

---

## 📱 ¿Tienes emulador? 

1. En Android Studio → AVD Manager → Abre un emulador
2. Ejecuta SETUP.bat
3. ¡Listo!

---

## 💾 ¿Quieres solo el APK?

El APK se genera automáticamente en:
```
app/build/outputs/apk/debug/app-debug.apk
```

Puedes:
- Arrastrarlo al emulador
- O instalar manualmente: `adb install -r app-debug.apk`

---

## 🎯 La App Incluye

✅ Crear/editar/eliminar tareas  
✅ Categorías y prioridades  
✅ Recordatorios automáticos  
✅ IA local con sugerencias  
✅ Base de datos local  
✅ UI moderna y bonita  
✅ **¡TODO 100% FUNCIONAL SIN EXTRAS!**

---

## 🆘 Si Algo Falla

### Error: "Java no encontrado"
- Reinstala Java 17
- Reinicia Windows después

### Error: "Android SDK no encontrado"
- Abre Android Studio
- Tools → SDK Manager
- Descarga API 34

### Error: "No se puede conectar al dispositivo"
- Abre Android Studio → AVD Manager
- Abre un emulador
- Intenta de nuevo

### Error en compilación
- Ejecuta: `gradlew.bat clean`
- Intenta de nuevo

---

## 📖 Documentación Completa

Si quieres aprender más:
- **START_HERE.md** - Guía detallada
- **README.md** - Descripción completa
- Otros archivos .md para detalles

---

## ⏱️ Tiempos Estimados

- Primera compilación: 3-5 minutos
- Instalación: 30 segundos
- **Total: ~5 minutos**

---

## 🎉 ¡Listo!

Ejecuta `SETUP.bat` (Windows) o `compilar_e_instalar.sh` (Mac/Linux) y la app se instalará automáticamente.

```
No necesitas hacer NADA más.
Todo está configurado.
```

**¿Problemas? Lee START_HERE.md o QUICK_START.md**
