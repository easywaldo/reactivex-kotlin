package com.example.rx.issue.service

import com.example.rx.issue.config.JWTProperties
import com.example.rx.issue.domain.User
import com.example.rx.issue.domain.UserRepository
import com.example.rx.issue.dto.SignInRequest
import com.example.rx.issue.dto.SignInResponse
import com.example.rx.issue.dto.SignRequest
import com.example.rx.issue.exception.PasswordNotMatchedException
import com.example.rx.issue.exception.UserNotFoundException
import com.example.rx.issue.utils.BCryptUtils
import com.example.rx.issue.utils.JWTClaim
import com.example.rx.issue.utils.JWTUtils
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class UserService (
    private val userRepository: UserRepository,
    private val jwtProperties: JWTProperties,
    private val cacheManager: CoroutineCacheManager<User>,
) {

    companion object {
        private val CACHE_TTL = Duration.ofMinutes(1)
    }

    suspend fun signUp(signUpRequest: SignRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw RuntimeException("이미 유저가 존재합니다.")
            }
            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                userName = username
            )
            userRepository.save(user)
        }
    }

    suspend fun signIn(singInRequest: SignInRequest): SignInResponse {
        return with(userRepository.findByEmail(singInRequest.email) ?: throw UserNotFoundException()) {
            val verified = BCryptUtils.verify(singInRequest.password, password)
            if (!verified) {
                throw PasswordNotMatchedException()
            }

            val jwtClaim = JWTClaim(
                userId = id!!,
                email = email,
                profileUrl = profileUrl,
                username = userName,
            )

            val token = JWTUtils.createToken(jwtClaim, jwtProperties)

            cacheManager.awaitPut(key = token, value = this, ttl = CACHE_TTL)

            SignInResponse(
                email=email,
                username = userName,
                token = token
            )
        }
    }

    suspend fun logout(token: String) {
        cacheManager.awaitEvict(token)
    }


}