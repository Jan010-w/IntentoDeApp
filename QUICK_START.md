# Quick Start - IntentoDeApp

## ⚡ Inicio Rápido (5 minutos)

### 1. Clonar/Descargar Proyecto
```bash
# Ya está en tu carpeta IntentoDeApp
cd IntentoDeApp
```

### 2. Abrir en Android Studio
```bash
# Click en "Open Project in Android Studio"
# O navega a la carpeta desde Android Studio
```

### 3. Dejar que Gradle sincronice
```
Android Studio mostrará "Gradle sync needed"
→ Click "Sync Now"
```

### 4. Ejecutar en Emulador
```bash
# Android Studio → Run "app" (Shift + F10)
# O crea un emulador en AVD Manager
```

### 5. ¡Prueba la App!
- ✅ Crea tu primera tarea
- ✅ Completa una tarea
- ✅ Elimina una tarea

---

## 🔧 Setup Backend (10 minutos)

### 1. Requisitos
- Node.js 16+ (descargar en nodejs.org)
- npm o yarn

### 2. Instalar Dependencias Backend
```bash
cd backend
npm install
```

### 3. Crear archivo .env
```bash
# Copia .env.example a .env
cp .env.example .env

# En Windows (PowerShell):
Copy-Item .env.example .env
```

### 4. Llenar .env
```
OPENAI_API_KEY=sk-xxxxxxxxxxxxxxx  # Tu key de OpenAI
PORT=3000
NODE_ENV=development
```

### 5. Iniciar Backend
```bash
npm run dev

# Deberías ver:
# 🚀 Servidor IntentoDeApp ejecutándose en puerto 3000
```

### 6. Verificar que Funciona
```bash
curl http://localhost:3000/health

# Resultado esperado:
# {"status":"ok","message":"Servidor IntentoDeApp activo"}
```

---

## 🎯 Próximos Pasos

### Fase 1: Personalización Básica
1. Cambiar colores en `ui/theme/Theme.kt`
2. Modificar strings en `res/values/strings.xml`
3. Ajustar iconos en `res/drawable/`

### Fase 2: Agregar Funcionalidades
1. Leer [DEVELOPMENT_ROADMAP.md](DEVELOPMENT_ROADMAP.md)
2. Seleccionar feature a implementar
3. Seguir el plan paso a paso

### Fase 3: Integración IA
1. Obtener API Key OpenAI
2. Configurar backend
3. Conectar desde la app
4. Probar sugerencias

---

## 📚 Documentación Rápida

| Archivo | Propósito |
|---------|-----------|
| [README.md](README.md) | Descripción general |
| [INSTALL.md](INSTALL.md) | Instalación detallada |
| [AI_INTEGRATION.md](AI_INTEGRATION.md) | Configurar IA |
| [CUSTOMIZATION_GUIDE.md](CUSTOMIZATION_GUIDE.md) | Personalizar |
| [DEVELOPMENT_ROADMAP.md](DEVELOPMENT_ROADMAP.md) | Plan de desarrollo |

---

## 🚀 Comandos Útiles

### Android
```bash
# Limpiar proyecto
./gradlew clean

# Compilar
./gradlew build

# Ejecutar tests
./gradlew test

# Generar APK
./gradlew assembleDebug
```

### Backend
```bash
# Instalar deps
npm install

# Ejecutar dev
npm run dev

# Ejecutar producción
npm start

# Ver logs
npm run dev 2>&1 | tee app.log
```

### Git (si lo usas)
```bash
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin <your-repo>
git push -u origin main
```

---

## ✅ Checklist de Verificación

### App Android
- [ ] Android Studio instalado
- [ ] SDK actualizado (API 34)
- [ ] Gradle sincronizado
- [ ] App abre sin errores
- [ ] Puedes crear tareas

### Backend
- [ ] Node.js instalado
- [ ] npm packages instalados
- [ ] .env configurado
- [ ] Servidor inicia sin errores
- [ ] /health responde

### Integración
- [ ] Backend URL correcta en AppModule.kt
- [ ] IA endpoint accesible
- [ ] Datos se sincronizan

---

## 🆘 Troubleshooting Rápido

### "Gradle sync failed"
```bash
./gradlew clean
./gradlew sync
```

### "Cannot connect to emulator"
```bash
adb kill-server
adb start-server
adb devices
```

### "Backend port already in use"
```bash
# Windows
netstat -ano | findstr :3000
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :3000
kill -9 <PID>
```

### "OpenAI API key invalid"
```
1. Ve a https://platform.openai.com/api-keys
2. Genera una nueva clave
3. Copia exactamente en .env
4. Reinicia el servidor
```

---

## 📞 Obtener Ayuda

1. **Documentación**: Lee los archivos .md
2. **Google**: "Android Kotlin Jetpack Compose"
3. **Stack Overflow**: Tag específico
4. **GitHub Issues**: Si encuentras bug

---

## 🎉 ¡Listo!

Ahora tienes una app completamente funcional con:
- ✅ Gestión de tareas
- ✅ Base de datos local
- ✅ Notificaciones
- ✅ Backend API
- ✅ Agente IA

**Siguiente**: Personaliza según tus necesidades.

---

**Última actualización**: Febrero 2026
**Versión**: 1.0.0
**Status**: Listo para usar ✅
