package xyz.udesh.lets

import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.router
import xyz.udesh.lets.handlers.IdeaHandler

val routerBean = beans {
    bean {
        router {
            "/ideas".nest {
                val handler = IdeaHandler(ref())

                GET("/stream") { handler.stream() }
                GET("/{id}") { handler.get(it) }
                GET("/") { handler.getAll(it) }
                POST("/") { handler.create(it) }
            }
        }
    }
}