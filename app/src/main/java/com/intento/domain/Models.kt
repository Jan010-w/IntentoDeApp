package com.intento.domain

import java.time.LocalDateTime

data class Task(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val category: String = "General",
    val priority: Priority = Priority.MEDIUM,
    val isCompleted: Boolean = false,
    val dueDate: LocalDateTime? = null,
    val reminderTime: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val tags: List<String> = emptyList()
)

enum class Priority {
    LOW, MEDIUM, HIGH, URGENT
}

data class TaskList(
    val id: Long = 0,
    val name: String,
    val color: String = "#4CAF50",
    val icon: String = "list",
    val createdAt: LocalDateTime = LocalDateTime.now()
)

data class Reminder(
    val id: Long = 0,
    val taskId: Long,
    val reminderTime: LocalDateTime,
    val isActive: Boolean = true
)
