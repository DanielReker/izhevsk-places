package io.github.danielreker.izhevskplaces.model

data class Recommendation(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val imageSlug: String
)
