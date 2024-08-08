package data.model

import kotlin.math.max

data class Caffeine(
    val id: Int,
    val amountMg: Int,
    val userId: Int,
    val createdAt: Long,
    val updatedAt: Long,
) {
    companion object {
        fun <T : Number> calculateScore(amountMg: T): Int {
            val amountMgInMg = amountMg.toDouble()
            return max(0, (amountMgInMg - 400).toInt())
        }
    }
}
