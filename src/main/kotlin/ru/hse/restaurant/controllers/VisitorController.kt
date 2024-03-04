package ru.hse.restaurant.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import ru.hse.restaurant.models.User
import ru.hse.restaurant.repositories.OrderingRepository

@RestController
class VisitorController(@Autowired orderingRepository: OrderingRepository) {
    lateinit var currVisitor: User


}