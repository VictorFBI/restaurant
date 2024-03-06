package ru.hse.restaurant.controllers

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.restaurant.models.Dish
import ru.hse.restaurant.models.Ordering
import ru.hse.restaurant.models.User
import ru.hse.restaurant.repositories.OrderingRepository
import ru.hse.restaurant.services.Kitchen
import java.math.BigDecimal
import java.time.LocalTime
import java.util.*

@RestController
class VisitorController(
    @Autowired private val orderingRepository: OrderingRepository,
    @Autowired private val kitchen: Kitchen
) {
    var currVisitor: User? = null

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostMapping("/createOrder")
    suspend fun createOrder(
        @RequestParam dishID: MutableList<Int>,
    ): Any {
        if (currVisitor == null) {
            return "No logged visitor"
        }
        val timeStart = LocalTime.now()
        var price: BigDecimal = BigDecimal.ZERO
        val dishes: MutableList<Dish> = mutableListOf()
        for (id in dishID) {
            val query = entityManager.createNativeQuery(
                "SELECT * FROM menu WHERE id = '$id'",
                Dish::class.java
            )
            val resultList = query.resultList
            if (resultList.isEmpty()) {
                return "Dish with id $id doesn't exist"
            }
            val dish = resultList.first() as Dish
            dishes.add(dish)
            price += dish.getPrice()
        }
        kitchen.serveOrder(dishes)
        val timeEnd = LocalTime.now()
        val order = Ordering(UUID.randomUUID(), dishID, timeStart, timeEnd, price, currVisitor!!.username)
        withContext(Dispatchers.IO) {
            orderingRepository.save(order)
        }
        return order
    }

}