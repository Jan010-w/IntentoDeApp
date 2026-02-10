package com.intento.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class, TaskListEntity::class, ReminderEntity::class],
    version = 1,
    exportSchema = false
)
abstract class IntentoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun taskListDao(): TaskListDao
    abstract fun reminderDao(): ReminderDao
}
