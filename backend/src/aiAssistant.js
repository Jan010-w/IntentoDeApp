// Módulo de IA para IntentoDeApp
const { OpenAI } = require('openai');

class AIAssistant {
    constructor(apiKey) {
        this.openai = new OpenAI({ apiKey });
    }

    async generateTaskSuggestions(tasks, context) {
        try {
            const prompt = `
                Dado el siguiente contexto de tareas:
                ${JSON.stringify(tasks, null, 2)}
                
                Contexto adicional: ${context}
                
                Sugiere 3 nuevas tareas que el usuario podría crear para mejorar su productividad.
                Responde en formato JSON con estructura: { suggestions: [{ title, description, priority }] }
            `;

            const response = await this.openai.chat.completions.create({
                model: 'gpt-3.5-turbo',
                messages: [{ role: 'user', content: prompt }],
                temperature: 0.7,
                max_tokens: 500
            });

            const content = response.choices[0].message.content;
            return JSON.parse(content).suggestions;
        } catch (error) {
            console.error('Error generando sugerencias:', error);
            throw error;
        }
    }

    async optimizeTasks(tasks) {
        try {
            const prompt = `
                Analiza estas tareas y reordénalas por prioridad:
                ${JSON.stringify(tasks, null, 2)}
                
                Responde con un array de tareas ordenadas, añadiendo un campo 'optimizedPriority'.
            `;

            const response = await this.openai.chat.completions.create({
                model: 'gpt-3.5-turbo',
                messages: [{ role: 'user', content: prompt }],
                temperature: 0.5,
                max_tokens: 800
            });

            const content = response.choices[0].message.content;
            return JSON.parse(content);
        } catch (error) {
            console.error('Error optimizando tareas:', error);
            throw error;
        }
    }

    async analyzeProductivity(tasks, completedTasks) {
        try {
            const prompt = `
                Analiza la productividad basándote en:
                - Tareas totales: ${tasks.length}
                - Tareas completadas: ${completedTasks.length}
                - Tasa de completitud: ${((completedTasks.length / tasks.length) * 100).toFixed(2)}%
                
                Proporciona insights y recomendaciones.
                Responde en JSON con estructura: { insights: string, score: number, recommendations: string[] }
            `;

            const response = await this.openai.chat.completions.create({
                model: 'gpt-3.5-turbo',
                messages: [{ role: 'user', content: prompt }],
                temperature: 0.6,
                max_tokens: 600
            });

            const content = response.choices[0].message.content;
            return JSON.parse(content);
        } catch (error) {
            console.error('Error analizando productividad:', error);
            throw error;
        }
    }

    async parseTaskDescription(description) {
        try {
            const prompt = `
                Convierte esta descripción natural en una tarea estructurada:
                "${description}"
                
                Extrae: título, descripción, prioridad (LOW/MEDIUM/HIGH/URGENT), fecha de vencimiento si aplica.
                Responde en JSON: { title: string, description: string, priority: string, dueDate: string|null }
            `;

            const response = await this.openai.chat.completions.create({
                model: 'gpt-3.5-turbo',
                messages: [{ role: 'user', content: prompt }],
                temperature: 0.7,
                max_tokens: 300
            });

            const content = response.choices[0].message.content;
            return JSON.parse(content);
        } catch (error) {
            console.error('Error parseando descripción:', error);
            throw error;
        }
    }
}

module.exports = { AIAssistant };
