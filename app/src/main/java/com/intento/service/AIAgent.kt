package com.intento.service

import android.content.Context
import com.intento.domain.Task
import java.time.LocalDateTime
import java.util.Random

data class AISuggestion(
    val title: String,
    val description: String,
    val priority: Int = 5
)

class AIAgent(private val context: Context) {

    private val random = Random()

    fun generateSuggestions(): List<AISuggestion> {
        val suggestions = mutableListOf<AISuggestion>()

        val sugestionsList = listOf(
            AISuggestion(
                title = "Organiza tu día",
                description = "Tienes muchas tareas. ¿Quieres que las reordene por prioridad?"
            ),
            AISuggestion(
                title = "Toma un descanso",
                description = "Parece que llevas trabajando mucho. Un pequeño descanso te ayudará"
            ),
            AISuggestion(
                title = "Revisa tareas antiguas",
                description = "Hay algunas tareas de hace días que aún están pendientes"
            ),
            AISuggestion(
                title = "Completa lo importante",
                description = "Las tareas urgentes deberían ser tu prioridad ahora"
            ),
            AISuggestion(
                title = "Productividad en aumento",
                description = "¡Vas muy bien! Mantén el ritmo completando más tareas"
            )
        )

        val randomIndex = random.nextInt(sugestionsList.size)
        suggestions.add(sugestionsList[randomIndex])

        return suggestions
    }

    fun suggestTaskCreation(context: String): List<Task> {
        return emptyList()
    }

    fun analyzeProductivity(): String {
        return "Estadísticas de productividad"
    }

    fun suggestNextTask(tasks: List<Task>): Task? {
        // Retorna la tarea más importante sin completar
        return tasks.filter { !it.isCompleted }
            .sortedWith(compareBy({ it.priority.ordinal }, { it.dueDate ?: LocalDateTime.MAX }))
            .firstOrNull()
    }
}
