package chatt.fullstack.frontend

import chatt.fullstack.frontend.framework.Http
import chatt.fullstack.shared.Post
import org.w3c.xhr.XMLHttpRequest

object Backend {
    const val BACKEND_BASE_URL = "http://localhost:9000"

    object Posts {
        private const val POST_URL = BACKEND_BASE_URL + "/post"

        fun put(post: Post, then: (XMLHttpRequest) -> Unit = {}) = Http.Json.put(POST_URL, post, then)
        fun get(id: String, then: (Post) -> Unit = {}) = Http.Json.get(POST_URL + "/$id", then)
        fun delete(id: String, then: (XMLHttpRequest) -> Unit = {}) = Http.Json.delete(POST_URL + "/$id", then)
    }

}
