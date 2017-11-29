package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.framework.Page
import kotlinx.html.dom.append
import kotlinx.html.js.button
import kotlinx.html.js.h1
import kotlinx.html.js.onClickFunction

val some = Page.create("/some") {
    append {
        val hey = h1 { +"Hey" }
        button {
            +"Remove hey"
            onClickFunction = {
                hey.remove()
            }
        }
    }
}