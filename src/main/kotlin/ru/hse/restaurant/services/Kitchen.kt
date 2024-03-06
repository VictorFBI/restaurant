package ru.hse.restaurant.services

import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import ru.hse.restaurant.models.Dish

@Service
class Kitchen {
    private suspend fun cookDish(dish: Dish) = coroutineScope {
        launch {
            println("Dish ${dish.getName()} is cooking...")
            delay((dish.getMinutes().toLong()) * 1000)
            println("Dish ${dish.getName()} was cooked!")
        }
    }

    suspend fun serveOrder(dishes: MutableList<Dish>) = coroutineScope {
        println("The order has started to be serviced...")
        val cookingJobs = dishes.map { dish ->
            async { cookDish(dish) }
        }
        cookingJobs.awaitAll()
        println("The order has finished to be serviced...")
        println("##########################################")
    }

}