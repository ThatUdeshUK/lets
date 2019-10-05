package xyz.udesh.lets

import org.springframework.context.support.beans
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.reactive.function.server.router
import xyz.udesh.lets.handlers.IdeaHandler

val routerBean = beans {
    bean {
        CorsWebFilter { CorsConfiguration().applyPermitDefaultValues() }
    }
    bean {
        router {
            "/ideas".nest {
                val handler = IdeaHandler(ref())

                GET("/stream") { handler.stream() }
                GET("/") { handler.getAll(it) }
                POST("/") { handler.create(it) }
            }
        }
    }
}