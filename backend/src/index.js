// IntentoDeApp Backend - Servidor Principal
// Este archivo contiene la configuración básica del servidor Node.js

require('dotenv').config();
const express = require('express');
const cors = require('cors');
const helmet = require('helmet');
const { AIAssistant } = require('./aiAssistant');

const app = express();
const PORT = process.env.PORT || 3000;

// Middlewares de seguridad
app.use(helmet());
app.use(cors());
app.use(express.json());

// Inicializar asistente IA
const aiAssistant = new AIAssistant(process.env.OPENAI_API_KEY);

// Rutas
app.get('/health', (req, res) => {
    res.json({ status: 'ok', message: 'Servidor IntentoDeApp activo' });
});

// Endpoint para generar sugerencias de tareas
app.post('/api/ai/suggestions', async (req, res) => {
    try {
        const { tasks, context } = req.body;
        const suggestions = await aiAssistant.generateTaskSuggestions(tasks, context);
        res.json({ success: true, suggestions });
    } catch (error) {
        console.error('Error en /api/ai/suggestions:', error);
        res.status(500).json({ success: false, error: error.message });
    }
});

// Endpoint para optimizar tareas
app.post('/api/ai/optimize', async (req, res) => {
    try {
        const { tasks } = req.body;
        const optimized = await aiAssistant.optimizeTasks(tasks);
        res.json({ success: true, optimized });
    } catch (error) {
        console.error('Error en /api/ai/optimize:', error);
        res.status(500).json({ success: false, error: error.message });
    }
});

// Endpoint para análisis de productividad
app.post('/api/ai/analysis', async (req, res) => {
    try {
        const { tasks, completedTasks } = req.body;
        const analysis = await aiAssistant.analyzeProductivity(tasks, completedTasks);
        res.json({ success: true, analysis });
    } catch (error) {
        console.error('Error en /api/ai/analysis:', error);
        res.status(500).json({ success: false, error: error.message });
    }
});

// Endpoint para crear tareas por descripción natural
app.post('/api/ai/create-task', async (req, res) => {
    try {
        const { description } = req.body;
        const task = await aiAssistant.parseTaskDescription(description);
        res.json({ success: true, task });
    } catch (error) {
        console.error('Error en /api/ai/create-task:', error);
        res.status(500).json({ success: false, error: error.message });
    }
});

// Manejo de errores 404
app.use((req, res) => {
    res.status(404).json({ error: 'Ruta no encontrada' });
});

// Iniciar servidor
app.listen(PORT, () => {
    console.log(`🚀 Servidor IntentoDeApp ejecutándose en puerto ${PORT}`);
    console.log(`📍 Health check: http://localhost:${PORT}/health`);
});

module.exports = app;
