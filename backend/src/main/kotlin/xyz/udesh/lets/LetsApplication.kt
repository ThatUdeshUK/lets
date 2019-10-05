package xyz.udesh.lets

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
class LetsApplication

fun main(args: Array<String>) {
    runApplication<LetsApplication>(*args) {
        addInitializers(routerBean)
    }
}

@EnableReactiveMongoRepositories(basePackageClasses = [])
class MongoConfig : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName() = "lets"

    override fun reactiveMongoClient() = mongoClient()

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create()

    @Bean
    override fun reactiveMongoTemplate() = ReactiveMongoTemplate(mongoClient(), databaseName)
}
