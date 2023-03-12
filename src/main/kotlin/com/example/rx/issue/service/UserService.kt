package com.example.rx.issue.service

import com.example.rx.issue.domain.User
import com.example.rx.issue.domain.UserRepository
import com.example.rx.issue.dto.SignRequest
import com.example.rx.issue.utils.BCryptUtils
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository,) {
    suspend fun signUp(signUpRequest: SignRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw RuntimeException("이미 유저가 존재합니다.")
            }
            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                userName = userName
            )
            userRepository.save(user)
        }
    }
}