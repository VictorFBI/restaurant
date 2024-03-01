package ru.hse.restaurant.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.restaurant.models.User
import ru.hse.restaurant.repositories.UserRepository

@RestController
class UserController(@Autowired private val userRepository: UserRepository) {

    @PostMapping("/createUser")
    fun createUser(
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam type: String
    ): User {
        val tmp = User(1, username, password, type)
        userRepository.save(tmp)
        return tmp
    }
}