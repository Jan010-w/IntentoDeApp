package com.intento.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intento.domain.Task
import com.intento.domain.Priority
import com.intento.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.LocalDateTime

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskUiState>(TaskUiState.Loading)
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    private val _selectedFilter = MutableStateFlow<TaskFilter>(TaskFilter.All)
    val selectedFilter: StateFlow<TaskFilter> = _selectedFilter.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            selectedFilter.flatMapLatest { filter ->
                when (filter) {
                    TaskFilter.All -> taskRepository.getAllTasks()
                    TaskFilter.Active -> taskRepository.getActiveTasks()
                    is TaskFilter.Category -> taskRepository.getTasksByCategory(filter.category)
                }
            }.collect { tasks ->
                _uiState.value = TaskUiState.Success(tasks)
            }
        }
    }

    fun setFilter(filter: TaskFilter) {
        _selectedFilter.value = filter
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
            loadTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
            loadTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
            loadTasks()
        }
    }

    fun toggleTaskCompletion(task: Task) {
        updateTask(task.copy(isCompleted = !task.isCompleted))
    }

    fun deleteCompletedTasks() {
        viewModelScope.launch {
            taskRepository.deleteCompletedTasks()
            loadTasks()
        }
    }
}

sealed class TaskUiState {
    object Loading : TaskUiState()
    data class Success(val tasks: List<Task>) : TaskUiState()
    data class Error(val message: String) : TaskUiState()
}

sealed class TaskFilter {
    object All : TaskFilter()
    object Active : TaskFilter()
    data class Category(val category: String) : TaskFilter()
}
