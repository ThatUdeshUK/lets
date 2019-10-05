package xyz.udesh.lets.data

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import xyz.udesh.lets.models.Idea

interface IdeaRepository : ReactiveMongoRepository<Idea, String>