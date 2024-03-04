package ru.hse.restaurant.models

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.sql.Time
import java.util.*

@Entity
@Table (name = "orderings")
class Ordering(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: UUID,
    @ElementCollection
    private val dishID: List<Int>,
    private val timeStarted: Time,
    private val timeEnded: Time,
    private val price: BigDecimal
) {
    constructor() : this(UUID.randomUUID(), listOf(), Time(0), Time(0), BigDecimal(0))
}
