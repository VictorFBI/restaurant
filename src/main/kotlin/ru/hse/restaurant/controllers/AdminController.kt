package ru.hse.restaurant.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.restaurant.models.Dish
import ru.hse.restaurant.models.User
import ru.hse.restaurant.repositories.MenuRepository
import java.math.BigDecimal

@RestController
class AdminController(@Autowired private val menuRepository: MenuRepository) {
    var currAdmin: User? = null

    @PostMapping("/createDish")
    fun createDish(
        @RequestParam name: String,
        @RequestParam description: String,
        @RequestParam price: BigDecimal,
        @RequestParam minutesToCook: Int
    ): Any {
        if (currAdmin == null) {
            return "No logged admin"
        }
        val dish = Dish(0, name, description, price, minutesToCook, currAdmin!!.username)
        return try {
            menuRepository.save(dish)
            dish
        } catch (e: Exception) {
            "This dish already exists"
        }
    }
}