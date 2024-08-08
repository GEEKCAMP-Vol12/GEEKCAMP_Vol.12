package data.model

import kotlin.math.max

data class Caffeine(
    val id: Int,
    val amountMg: Int,
    val userId: Int,
    val createdAt: Long,
    val updatedAt: Long,
) {
    fun calculateScore(): Int = max(0, amountMg - 400)
}
