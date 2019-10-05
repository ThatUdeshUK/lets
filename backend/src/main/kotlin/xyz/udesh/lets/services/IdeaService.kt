package xyz.udesh.lets.services

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import xyz.udesh.lets.data.IdeaRepository
import xyz.udesh.lets.models.Idea
import java.util.*

@Service
class IdeaService(
        private val ideaRepository: IdeaRepository
) {

    private val ideas = EmitterProcessor.create<Idea>()

    fun stream(): Flux<Idea> {
        return ideas.toFlux()
    }

    fun get(ideaId: String): Mono<Idea> {
        return ideaRepository.findById(ideaId)
    }

    fun getAll(limit: Optional<Long>, offset: Optional<Long>): Flux<Idea> {
        return ideaRepository.findAll()
                .skip(limit.orElse(50).times(offset.orElse(0)))
                .take(limit.orElse(50))
    }

    fun create(idea: Idea): Mono<Idea> {
        return ideaRepository.save(idea)
                .doOnNext { ideas.onNext(it) }
    }

}