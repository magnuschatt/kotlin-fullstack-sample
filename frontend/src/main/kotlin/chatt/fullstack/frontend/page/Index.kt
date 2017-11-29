package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import chatt.fullstack.frontend.page.component.searchBar
import chatt.fullstack.frontend.page.component.topNavigationBar
import kotlinx.html.InputType
import kotlinx.html.dom.append
import kotlinx.html.js.*

val index = Page.create("/") {
    append {
        topNavigationBar()
        searchBar()
        h1 { +"Index" }

        div {
            button {
                onClickFunction = {
                    Pages.switchTo(createPost)
                }
                +"Create post"
            }
        }

        br()
        div {
            form {
                input(type = InputType.text, name = "search") {
                    placeholder = "Search posts.."
                }
            }
        }
    }
}




