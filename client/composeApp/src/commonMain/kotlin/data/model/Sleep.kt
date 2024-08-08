package data.model

import kotlin.math.max

data class Sleep(
    val id: Int,
    val lengthMin: Int,
    val userId: Int,
    val createdAt: Long,
    val updatedAt: Long,
) {
    companion object {
        fun <T : Number> calculateScore(lengthMin: T): Int {
            val lengthMinInMinutes = lengthMin.toDouble()
            return max(0, (60 * 7 - lengthMinInMinutes).toInt())
        }
    }
}
