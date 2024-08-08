package data.model

data class User(
    val id: Int,
    val name: String,
    val icon: String,
    val age: Int,
    val gender: Int,
    val createdAt: Long,
    val updatedAt: Long,
)
