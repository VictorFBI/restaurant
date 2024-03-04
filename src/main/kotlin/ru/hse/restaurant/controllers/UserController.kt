package ru.hse.restaurant.controllers

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.restaurant.models.User
import ru.hse.restaurant.repositories.UserRepository
import java.util.*

@RestController
class UserController(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val adminController: AdminController,
    @Autowired private val visitorController: VisitorController
) {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private lateinit var currUser: User

    @PostMapping("/signUp")
    fun createUser(
        @RequestParam username: String, @RequestParam password: String, @RequestParam type: String
    ): Any {
        val tmp = User(UUID.randomUUID(), username, password, type)
        try {
            userRepository.save(tmp)
        } catch (e: Exception) {
            return "User already exists"
        }
        return tmp
    }

    @GetMapping("/signIn")
    fun login(
        @RequestParam username: String, @RequestParam password: String, @RequestParam type: String
    ): Any {
        val query = entityManager.createNativeQuery(
            "SELECT * FROM users WHERE username = '$username' AND password = '$password' AND type = '$type'",
            User::class.java
        )
        val resultList = query.resultList
        if (resultList.isEmpty()) {
            return "User is not found"
        }
        currUser = resultList.first() as User
        if (currUser.type == "visitor") {
            visitorController.currVisitor = currUser
        } else {
            adminController.currAdmin = currUser
        }
        return currUser
    }

}