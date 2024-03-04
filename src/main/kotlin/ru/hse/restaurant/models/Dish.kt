package ru.hse.restaurant.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.sql.Time

@Entity
@Table(name = "menu")
class Dish(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Int,
    private val name: String,
    private val description: String,
    private val price: BigDecimal,
    private val timeToCook: Time
) {
    constructor() : this(0, "", "", BigDecimal(0), Time(0))
}
