package com.intento.service

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class ReminderScheduler(private val context: Context) {

    fun scheduleReminder(taskId: Long, minutesFromNow: Long) {
        val reminderRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(minutesFromNow, TimeUnit.MINUTES)
            .setInputData(
                workDataOf("taskId" to taskId)
            )
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "reminder_$taskId",
            ExistingWorkPolicy.REPLACE,
            reminderRequest
        )
    }

    fun schedulePeriodicAICheck(intervalMinutes: Long = 30) {
        val aiCheckRequest = PeriodicWorkRequestBuilder<AIAgentWorker>(
            intervalMinutes,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "ai_agent_check",
            ExistingPeriodicWorkPolicy.KEEP,
            aiCheckRequest
        )
    }

    fun cancelReminder(taskId: Long) {
        WorkManager.getInstance(context).cancelUniqueWork("reminder_$taskId")
    }
}
