package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.Backend
import chatt.fullstack.frontend.framework.Html
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.page.component.topNavigationBar
import kotlinx.html.dom.append
import kotlinx.html.js.div
import kotlinx.html.js.h1
import kotlinx.html.js.h3
import kotlinx.html.js.p

val viewPost = Page.create("/post/view") {
    append {
        topNavigationBar()
        h1 { +"View post" }
    }

    val id = Html.queryParams["id"]!!
    Backend.Posts.get(id) {
        append {
            div {
                h3 { +it.title }
                p { +it.content }
            }
        }
    }
}
