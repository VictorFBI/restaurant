package ru.hse.restaurant.models

import jakarta.persistence.*
import lombok.Data

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    val id: Long,
    val username: String,
    val password: String,
    val type: String
) {
    constructor() : this(1, "", "", "")
}