package com.intento.repository

import com.intento.data.*
import com.intento.domain.Task
import com.intento.domain.TaskList
import com.intento.domain.Reminder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val reminderDao: ReminderDao,
    private val taskListDao: TaskListDao
) {
    fun getAllTasks(): Flow<List<Task>> =
        taskDao.getAllTasks().map { entities ->
            entities.map { it.toTask() }
        }

    fun getActiveTasks(): Flow<List<Task>> =
        taskDao.getActiveTasks().map { entities ->
            entities.map { it.toTask() }
        }

    fun getTasksByCategory(category: String): Flow<List<Task>> =
        taskDao.getTasksByCategory(category).map { entities ->
            entities.map { it.toTask() }
        }

    suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(task.toEntity())
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task.toEntity())
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.toEntity())
    }

    suspend fun deleteCompletedTasks() {
        taskDao.deleteCompletedTasks()
    }

    fun getActiveReminders(): Flow<List<Reminder>> =
        reminderDao.getActiveReminders().map { entities ->
            entities.map { it.toReminder() }
        }

    suspend fun insertReminder(reminder: Reminder): Long {
        return reminderDao.insertReminder(reminder.toEntity())
    }

    suspend fun updateReminder(reminder: Reminder) {
        reminderDao.updateReminder(reminder.toEntity())
    }

    fun getAllLists(): Flow<List<TaskList>> =
        taskListDao.getAllLists().map { entities ->
            entities.map { it.toTaskList() }
        }

    suspend fun insertList(list: TaskList): Long {
        return taskListDao.insertList(list.toEntity())
    }

    suspend fun updateList(list: TaskList) {
        taskListDao.updateList(list.toEntity())
    }

    suspend fun deleteList(list: TaskList) {
        taskListDao.deleteList(list.toEntity())
    }

    private fun TaskEntity.toTask(): Task =
        Task(
            id = id,
            title = title,
            description = description,
            category = category,
            priority = com.intento.domain.Priority.valueOf(priority),
            isCompleted = isCompleted,
            dueDate = dueDate?.let { java.time.Instant.ofEpochMilli(it).atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() },
            reminderTime = reminderTime?.let { java.time.Instant.ofEpochMilli(it).atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() },
            createdAt = java.time.Instant.ofEpochMilli(createdAt).atZone(java.time.ZoneId.systemDefault()).toLocalDateTime(),
            updatedAt = java.time.Instant.ofEpochMilli(updatedAt).atZone(java.time.ZoneId.systemDefault()).toLocalDateTime(),
            tags = if (tags.isEmpty()) emptyList() else com.google.gson.Gson().fromJson(tags, Array<String>::class.java).toList()
        )

    private fun Task.toEntity(): TaskEntity =
        TaskEntity(
            id = id,
            title = title,
            description = description,
            category = category,
            priority = priority.toString(),
            isCompleted = isCompleted,
            dueDate = dueDate?.atZone(java.time.ZoneId.systemDefault())?.toInstant()?.toEpochMilli(),
            reminderTime = reminderTime?.atZone(java.time.ZoneId.systemDefault())?.toInstant()?.toEpochMilli(),
            createdAt = createdAt.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
            updatedAt = updatedAt.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
            tags = if (tags.isEmpty()) "" else com.google.gson.Gson().toJson(tags)
        )

    private fun ReminderEntity.toReminder(): Reminder =
        Reminder(
            id = id,
            taskId = taskId,
            reminderTime = java.time.Instant.ofEpochMilli(reminderTime).atZone(java.time.ZoneId.systemDefault()).toLocalDateTime(),
            isActive = isActive
        )

    private fun Reminder.toEntity(): ReminderEntity =
        ReminderEntity(
            id = id,
            taskId = taskId,
            reminderTime = reminderTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
            isActive = isActive
        )

    private fun TaskListEntity.toTaskList(): TaskList =
        TaskList(
            id = id,
            name = name,
            color = color,
            icon = icon,
            createdAt = java.time.Instant.ofEpochMilli(createdAt).atZone(java.time.ZoneId.systemDefault()).toLocalDateTime()
        )

    private fun TaskList.toEntity(): TaskListEntity =
        TaskListEntity(
            id = id,
            name = name,
            color = color,
            icon = icon,
            createdAt = createdAt.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
        )
}
