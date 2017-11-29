package chatt.fullstack.frontend.page.component

import chatt.fullstack.frontend.framework.Pages
import chatt.fullstack.frontend.page.index
import kotlinx.html.TagConsumer
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLElement

const val TOP_NAVIGATION_BAR = "top-navigation-bar"

fun TagConsumer<HTMLElement>.topNavigationBar() {
    div(classes = TOP_NAVIGATION_BAR) {
        div(classes = CONSTRAINED) {

            div(classes = LEFT) {
                button {
                    +"Fullstack"
                    onClickFunction = { Pages.switchTo(index) }
                }
            }
            div(classes = RIGHT) {
                button { +"Login" }
            }
        }
    }
}