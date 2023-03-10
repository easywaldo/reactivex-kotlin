package com.example.rx.issue.controller

import com.example.rx.issue.dto.SignRequest
import com.example.rx.issue.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController (private val userService: UserService,
) {
  @PostMapping("/signup")
  suspend fun signUp(@RequestBody request: SignRequest) {
      userService.signUp(request)
  }
}