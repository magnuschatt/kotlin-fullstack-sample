package chatt.fullstack.frontend.page

import chatt.fullstack.frontend.framework.Html
import chatt.fullstack.frontend.framework.Page
import chatt.fullstack.frontend.framework.Pages
import kotlinx.html.dom.append
import kotlinx.html.js.*

val page1: Page = Page.create("/page1") {
    append {

        h1 {
            +"H1 title on page1"
        }

        button {
            +"Html button"
        }

        br()

        div(classes = "greybox") {
            div(classes = "whitebox") {
                p {
                    b { +"Div inside div inside p inside b" }
                }
            }
        }

        br()

        input {
            placeholder = "html input"
        }

        br()

        val input = input {
            placeholder = "input where we can refer to the value later"
        }

        br()

        button {
            +"Print input value"
            onClickFunction = {
                println(input.value)
                otherFunction(input.value)
            }
        }

        button {
            +"Go to page2"
            onClickFunction = { Pages.switchTo(page2) }
        }

        val bob: String? = Html.queryParams["bob"]
        println(bob)

    }
}

private fun otherFunction(value: String) {
    println("some function prints: $value")
}