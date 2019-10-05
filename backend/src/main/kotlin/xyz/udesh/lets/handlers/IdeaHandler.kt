package xyz.udesh.lets.handlers

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import xyz.udesh.lets.dto.ResponseDto
import xyz.udesh.lets.models.Idea
import xyz.udesh.lets.services.IdeaService
import xyz.udesh.lets.toResponse

class IdeaHandler(
        private val ideaService: IdeaService
) {

    fun stream() = ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(ideaService.stream(), Idea::class.java)

    fun get(request: ServerRequest) = ServerResponse.ok()
            .body(ideaService.get(request.pathVariable("id")), Idea::class.java)

    fun getAll(request: ServerRequest) = ServerResponse.ok()
            .body(
                    ideaService.getAll(
                            limit = request.queryParam("limit").map { it.toLong() },
                            offset = request.queryParam("offset").map { it.toLong() }
                    ),
                    Idea::class.java
            )

    fun create(request: ServerRequest) = ServerResponse.ok()
            .body(
                    request.bodyToMono(Idea::class.java)
                            .flatMap { ideaService.create(it) }
                            .map { "Idea created successfully".toResponse() },
                    ResponseDto::class.java
            )

}