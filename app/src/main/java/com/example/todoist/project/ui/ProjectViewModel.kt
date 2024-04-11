package com.example.todoist.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.project.domain.usecase.GetProjects
import com.example.todoist.project.ui.model.toProjectView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val getProjects: GetProjects
) : ViewModel() {

    private val _projectState = MutableStateFlow(ProjectState())
    val projectState: MutableStateFlow<ProjectState>
        get() = _projectState


    fun getProjectList() {
        viewModelScope.launch {
            getProjects().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        result.data?.let { projects ->
                            _projectState.value = _projectState.value.copy(
                                projects = projects.map {
                                    it.toProjectView()
                                }
                            )
                        }
                    }

                    is NetworkResult.Error -> {

                    }

                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Exception -> {

                    }
                }
            }
        }
    }
}