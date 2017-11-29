package chatt.fullstack.frontend.page.component

import kotlinx.html.InputType
import kotlinx.html.TagConsumer
import kotlinx.html.js.div
import kotlinx.html.js.input
import org.w3c.dom.HTMLElement

const val SEARCH_BAR = "search-bar"

fun TagConsumer<HTMLElement>.searchBar() {
    div(classes = SEARCH_BAR) {
        input(type = InputType.text, name = "search") {
            placeholder = "Search posts.."
        }
    }
}