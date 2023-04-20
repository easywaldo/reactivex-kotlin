package com.example.rx.issue.controller

import com.example.rx.issue.dto.SignInRequest
import com.example.rx.issue.dto.SignInResponse
import com.example.rx.issue.dto.SignRequest
import com.example.rx.issue.model.AuthToken
import com.example.rx.issue.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController (private val userService: UserService,
) {
  @PostMapping("/signup")
  suspend fun signUp(@RequestBody request: SignRequest) {
      userService.signUp(request)
  }

    @PostMapping("/signin")
    suspend fun signIn(@RequestBody singInRequest: SignInRequest): SignInResponse {
        return userService.signIn(singInRequest)
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun logout(@AuthToken token: String) {
        userService.logout(token)
    }
}