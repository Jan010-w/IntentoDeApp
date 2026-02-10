package com.intento

import android.content.Context
import androidx.room.Room
import com.intento.data.IntentoDatabase
import com.intento.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIntentoDatabase(
        @ApplicationContext context: Context
    ): IntentoDatabase {
        return Room.databaseBuilder(
            context,
            IntentoDatabase::class.java,
            "intento_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTaskRepository(database: IntentoDatabase): TaskRepository {
        return TaskRepository(
            database.taskDao(),
            database.reminderDao(),
            database.taskListDao()
        )
    }
}
