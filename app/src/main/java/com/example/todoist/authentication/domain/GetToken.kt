package com.example.todoist.authentication.domain

import com.example.todoist.authentication.data.repository.AuthenticationRepository
import javax.inject.Inject

class GetToken @Inject constructor(private val authenticationRepository: AuthenticationRepository) {
    operator fun invoke(code: String) = authenticationRepository.getToken(code)
}