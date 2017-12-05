package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.Backend
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import kotlinx.html.dom.append
import kotlinx.html.js.*

val index: Page = Page.create("/") {
    append {
        button {
            +"Home"
            onClickFunction = { Pages.renderCurrent() }
        }

        h1 {
            +"Index"
        }

        button {
            +"Create Post"
            onClickFunction = { Pages.switchTo(createPost) }
        }
    }

    Backend.Posts.fetchAll { posts ->
        append {
            br()
            div(classes = "greybox") {
                posts.forEach { post ->
                    button {
                        +post.title
                        onClickFunction = {
                            Pages.switchTo(viewPost, mapOf("id" to post.id))
                        }
                    }
                }
            }
        }
    }

}