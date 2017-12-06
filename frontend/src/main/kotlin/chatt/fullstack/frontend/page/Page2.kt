package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.Backend
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import kotlinx.html.dom.append
import kotlinx.html.js.*

val page2: Page = Page.create("/page2") {

    append {

        h1 {
            +"H1 title on page2"
        }

        textArea {
            maxLength = "200"
        }

        br()

        button {
            +"Go to page1"
            onClickFunction = { Pages.switchTo(page1, mapOf("bob" to "john")) }
        }

        br()

    }

    Backend.Posts.fetchAll { posts ->
        append {
            posts.forEach { post ->

                button {
                    +post.title
                }

                br()

            }
        }
    }

}