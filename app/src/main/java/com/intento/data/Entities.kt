package com.intento.data

import androidx.room.*
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String = "",
    val category: String = "General",
    val priority: String = "MEDIUM",
    val isCompleted: Boolean = false,
    val dueDate: Long? = null,
    val reminderTime: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val tags: String = "" // JSON string
)

@Entity(
    tableName = "task_lists",
    indices = [Index("name")]
)
data class TaskListEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val color: String = "#4CAF50",
    val icon: String = "list",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "reminders",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("taskId")]
)
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val taskId: Long,
    val reminderTime: Long,
    val isActive: Boolean = true
)
