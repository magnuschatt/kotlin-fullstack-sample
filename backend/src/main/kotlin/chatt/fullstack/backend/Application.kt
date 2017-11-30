package chatt.fullstack.backend

import chatt.fullstack.shared.Post
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.put

@Suppress("unused")
fun Application.main() {

    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {}
    }

    install(CORS) {
        methods.add(HttpMethod.Put)
        methods.add(HttpMethod.Get)
        methods.add(HttpMethod.Delete)
        host("localhost:80")
    }

    install(StatusPages) {
        exception<MissingKotlinParameterException> { exc ->
            call.respond(HttpStatusCode(422, "Unprocessable Entity"), exc.msg)
        }
    }

    install(Routing) {

        val postDao = InMemoryPostDao()

        put("/post") {
            val post = call.receive<Post>()
            val replaced = postDao.insertOrReplace(post)
            val response = if (replaced) HttpStatusCode.OK else HttpStatusCode.Created
            call.respond(response)
        }

        get("/post/{id}") {
            val id = call.parameters["id"]!!
            val response = postDao.fetchOneById(id) ?: HttpStatusCode.NotFound
            call.respond(response)
        }

        get("/post") {
            val posts = postDao.fetchAll()
            call.respond(posts)
        }
    }

}
