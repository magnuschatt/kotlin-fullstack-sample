package chatt.fullstack.backend

import chatt.fullstack.shared.Post
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.put

@Suppress("unused")
fun Application.main() {

    install(CallLogging) // log incoming calls

    install(DefaultHeaders) {
        header("Cache-Control", "no-cache, no-store, must-revalidate") // disable caching
        header("Pragma", "no-cache") // disable caching
        header("Expires", "0") // disable caching
    }

    install(ContentNegotiation) {
        jackson {} // Register Jackson as the JSON parser
    }

    install(CORS) { // enable CORS for the frontend
        methods.add(HttpMethod.Put)
        methods.add(HttpMethod.Get)
        host("localhost:80")
    }

    // hint for returning 422 (Unprocessable Entity):
    // http://ktor.io/features/status-pages.html

    install(Routing) {

        put("/example1") {
            val post = call.receive<Post>() // receiving incoming json
            call.respond(post)              // responding with json
        }

        get("/example2/{bob}") {
            val bob = call.parameters["bob"]!!
            println(bob)
            call.respond(HttpStatusCode.NotImplemented)
        }

        // Missing:
        // GET /post         (get all)
        // GET /post/{id}    (get by id)
        // PUT /post
        // DELETE /post/{id}

    }

}
