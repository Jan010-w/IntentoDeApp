# Integración de IA - IntentoDeApp

## Descripción General

El sistema de IA de IntentoDeApp proporciona asistencia inteligente para la gestión de tareas. Se compone de dos partes:

1. **Backend Node.js** - Servidor que procesa solicitudes de IA
2. **Agente Local** - Procesamiento básico en la app Android

## 🚀 Arquitectura

```
App Android
    ↓
AIAgent (procesamiento local)
    ↓
API REST (Retrofit)
    ↓
Backend Node.js
    ↓
OpenAI API
```

## 📋 Capacidades de IA

### 1. Generación de Sugerencias
```
Input: Lista de tareas actuales
Output: 3 nuevas tareas recomendadas basadas en patrones
```

### 2. Optimización de Prioridades
```
Input: Todas las tareas del usuario
Output: Tareas reordenadas por importancia y urgencia
```

### 3. Análisis de Productividad
```
Input: Historial de tareas completadas
Output: Gráficos, métricas y recomendaciones de mejora
```

### 4. Parseo Natural de Texto
```
Input: "Comprar leche mañana"
Output: { title: "Comprar leche", priority: "MEDIUM", dueDate: "2024-02-11" }
```

## 🔧 Configuración del Backend

### 1. Instalar Dependencias

```bash
cd backend
npm install
```

### 2. Crear archivo .env

```env
PORT=3000
OPENAI_API_KEY=sk-xxxxxxxxxxxxxxxxxxxxxxxx
NODE_ENV=development
```

### 3. Obtener API Key de OpenAI

1. Ve a https://platform.openai.com/api-keys
2. Crea una nueva clave API
3. Cópiala a tu archivo `.env`

### 4. Iniciar el Servidor

```bash
npm start          # Producción
npm run dev        # Desarrollo (con hot reload)
```

El servidor estará disponible en: `http://localhost:3000`

## 📡 Endpoints de API

### Health Check
```
GET /health
Response: { status: "ok", message: "..." }
```

### Generar Sugerencias
```
POST /api/ai/suggestions
Body: { tasks: Task[], context: string }
Response: { success: true, suggestions: Suggestion[] }
```

### Optimizar Tareas
```
POST /api/ai/optimize
Body: { tasks: Task[] }
Response: { success: true, optimized: Task[] }
```

### Analizar Productividad
```
POST /api/ai/analysis
Body: { tasks: Task[], completedTasks: Task[] }
Response: { success: true, analysis: { insights, score, recommendations } }
```

### Crear Tarea desde Descripción
```
POST /api/ai/create-task
Body: { description: string }
Response: { success: true, task: Task }
```

## 🔌 Integración en la App Android

### 1. Configurar URL del Backend

En [AppModule.kt](app/src/main/java/com/intento/AppModule.kt):

```kotlin
@Provides
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")  // Emulador
        // o
        .baseUrl("http://192.168.1.XXX:3000/")  // Dispositivo físico
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
```

### 2. Crear Servicio Retrofit

```kotlin
interface AIService {
    @POST("/api/ai/suggestions")
    suspend fun getSuggestions(@Body request: SuggestionsRequest): SuggestionsResponse
    
    @POST("/api/ai/create-task")
    suspend fun createTaskFromDescription(@Body request: DescriptionRequest): TaskResponse
}
```

### 3. Usar en ViewModel

```kotlin
viewModelScope.launch {
    try {
        val suggestions = aiService.getSuggestions(...)
        // Mostrar sugerencias al usuario
    } catch (e: Exception) {
        // Manejar error
    }
}
```

## 🎯 Casos de Uso

### Caso 1: Sugerencias Automáticas
1. Usuario abre la app
2. App envía sus tareas actuales al backend
3. IA sugiere 3 nuevas tareas
4. Usuario elige crear una sugerencia

### Caso 2: Crear Tarea por Voz/Texto
1. Usuario dice: "Comprar leche mañana por la tarde"
2. App envía al backend: `{ description: "Comprar leche mañana por la tarde" }`
3. IA parsea y retorna: `{ title: "Comprar leche", priority: "MEDIUM", dueDate: "2024-02-11 14:00" }`
4. App crea la tarea automáticamente

### Caso 3: Optimización de Prioridades
1. Usuario tiene 10 tareas
2. Abre menú "Optimizar"
3. App envía todas las tareas al backend
4. IA reordena por importancia
5. App actualiza el orden en la interfaz

## 🤖 Modelos de IA Disponibles

- **gpt-3.5-turbo** (Recomendado) - Rápido y económico
- **gpt-4** (Premium) - Más preciso pero más lento

Cambiar en [aiAssistant.js](backend/src/aiAssistant.js):

```javascript
model: 'gpt-4',  // Cambiar aquí
```

## 💡 Mejoras Futuras

1. **Modelo Local** - Usar TensorFlow Lite para procesamiento sin internet
2. **Fine-tuning** - Entrenar modelo con datos de usuario
3. **Voz** - Integración con Google Speech-to-Text
4. **Machine Learning** - Predicción de tiempo de tarea
5. **Análisis Avanzado** - Gráficos y reportes detallados

## 🔒 Consideraciones de Seguridad

- ✅ Nunca exponer claves API en el cliente
- ✅ Usar variables de entorno (.env)
- ✅ Validar y sanitizar entrada del usuario
- ✅ Limitar rate limiting en endpoints
- ✅ Encriptar datos sensibles en transmisión

## 📊 Costos de OpenAI

- GPT-3.5-turbo: ~$0.0005 por 1K tokens
- Estimado: ~$5/mes si usas la app activamente

## Troubleshooting

### Error: "Cannot connect to backend"
- ✓ Verificar que el servidor está corriendo: `curl http://localhost:3000/health`
- ✓ Verificar URL correcta en AppModule.kt
- ✓ Si usas emulador, la URL debe ser `http://10.0.2.2:3000`

### Error: "Invalid API Key"
- ✓ Verificar que la clave está correcta en `.env`
- ✓ Verificar que la clave no expiró
- ✓ Verificar que tienes créditos en OpenAI

### Respuestas Lentas
- ✓ Usar gpt-3.5-turbo en lugar de gpt-4
- ✓ Reducir cantidad de tokens en prompts
- ✓ Implementar caché de respuestas

---

**¿Necesitas help?** Revisa los logs:

```bash
# Backend
npm run dev  # Muestra logs en tiempo real

# App
adb logcat com.intento
```
