package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.Backend
import chatt.fullstack.frontend.framework.Html
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import kotlinx.html.dom.append
import kotlinx.html.js.*

val viewPost = Page.create("/post/view") {
    append {
        button {
            +"Fullstack"
            onClickFunction = { Pages.switchTo(index) }
        }

        h1 {
            +"View Post"
        }
    }

    val id = Html.queryParams["id"]!!
    Backend.Posts.get(id) { post ->
        append {
            div(classes = "textview") {
                p { b { +post.title } }
                p { +post.content }
            }
        }
    }

}