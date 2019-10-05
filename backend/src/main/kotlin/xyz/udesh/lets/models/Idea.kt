package xyz.udesh.lets.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "idea")
data class Idea(
        @Id val id: String?,
        val title: String
)