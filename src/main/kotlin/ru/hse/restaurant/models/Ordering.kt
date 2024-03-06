package ru.hse.restaurant.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalTime
import java.util.*

@Entity
@Table(name = "orderings")
class Ordering(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: UUID,
    @ElementCollection
    private val dishID: MutableList<Int>,
    private val timeStarted: LocalTime,
    private val timeEnded: LocalTime,
    private val price: BigDecimal,
    private val customer: String
) {
    constructor() : this(UUID.randomUUID(), mutableListOf(), LocalTime.now(), LocalTime.now(), BigDecimal(0), "")
}
