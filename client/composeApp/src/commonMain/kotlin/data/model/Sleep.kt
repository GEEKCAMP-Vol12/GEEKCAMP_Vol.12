package data.model

import kotlin.math.max

data class Sleep(
    val id: Int,
    val lengthMin: Int,
    val userId: Int,
    val createdAt: Long,
    val updatedAt: Long,
) {
    fun calculateScore(): Int = max(0, 60 * 7 - lengthMin)
}
