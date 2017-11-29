package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.Backend
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import chatt.fullstack.frontend.page.component.topNavigationBar
import chatt.fullstack.shared.Post
import kotlinx.html.InputType
import kotlinx.html.dom.append
import kotlinx.html.js.*

val createPost = Page.create("/post/create") {
    append {
        topNavigationBar()
        h1 { +"Create post" }

        div {
            val postTitle = input(type = InputType.text) { placeholder = "Title" }
            br()
            val postBody = textArea(rows = "5", cols = "40") { placeholder = "Body"}
            br()
            button {
                +"Submit"
                onClickFunction = {
                    submitPost(postTitle.value, postBody.value)
                }
            }
        }
    }
}

private fun submitPost(title: String, body: String) {
    val id = body.hashCode().toString()
    val post = Post(id, title, body)

    Backend.Posts.put(post) {
        Pages.switchTo(viewPost, mapOf("id" to id))
    }

}