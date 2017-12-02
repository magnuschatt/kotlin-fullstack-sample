package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.Backend
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import chatt.fullstack.shared.Post
import kotlinx.html.InputType
import kotlinx.html.dom.append
import kotlinx.html.js.*
import kotlin.math.absoluteValue

val createPost = Page.create("/post/create") {
    append {
        button {
            +"Home"
            onClickFunction = { Pages.switchTo(index) }
        }

        h1 {
            +"Create Post"
        }

        val input = input(type = InputType.text) {
            placeholder = "Title"
        }

        br()

        val textArea = textArea {
            maxLength = "200"
            placeholder = "Content"
        }

        br()

        button {
            +"Submit"
            onClickFunction = { submitPost(input.value, textArea.value) }
        }
    }
}

private fun submitPost(title: String, content: String) {
    if (title.isBlank() || content.isBlank()) return
    val id = "" + (title + content).hashCode().absoluteValue // not good, but works for this example
    val post = Post(id, title, content)
    Backend.Posts.insertOrReplace(post) {
        Pages.switchTo(viewPost, mapOf("id" to id))
    }
}