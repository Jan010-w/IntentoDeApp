package com.intento.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.intento.repository.TaskRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ReminderWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        return try {
            val taskId = inputData.getLong("taskId", -1)
            if (taskId != -1L) {
                val notificationService = NotificationService(applicationContext)
                notificationService.showTaskReminder(
                    taskId = taskId,
                    taskTitle = "Recordatorio de Tarea",
                    taskDescription = "Tienes una tarea pendiente"
                )
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

class AIAgentWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        return try {
            val notificationService = NotificationService(applicationContext)
            val aiAgent = AIAgent(applicationContext)

            // Aquí va la lógica del agente IA
            val suggestions = aiAgent.generateSuggestions()

            if (suggestions.isNotEmpty()) {
                notificationService.showAIAssistantSuggestion(
                    title = suggestions[0].title,
                    message = suggestions[0].description
                )
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
