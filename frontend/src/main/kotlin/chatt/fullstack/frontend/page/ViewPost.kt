package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.Backend
import chatt.fullstack.frontend.framework.Html
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import kotlinx.html.dom.append
import kotlinx.html.js.*

val viewPost: Page = Page.create("/post/view") {
    append {
        button {
            +"Home"
            onClickFunction = { Pages.switchTo(index) }
        }

        h1 {
            +"View Post"
        }
    }

    val id = Html.queryParams["id"]!!
    Backend.Posts.fetchOneById(id) { post ->
        append {
            div(classes = "whitebox") {
                p { b { +post.title } }
                p { +post.content }
            }
        }
    }

}