package ru.hse.restaurant.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.restaurant.models.Ordering
import java.util.*

interface OrderingRepository : JpaRepository<Ordering, UUID>