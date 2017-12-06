@file:Suppress("unused")

package chatt.fullstack.frontend

import chatt.fullstack.frontend.framework.Pages
import chatt.fullstack.frontend.page.page1
import chatt.fullstack.frontend.page.page2

fun main(args: Array<String>) {

    Pages.register(page1)
    Pages.register(page2)

    Pages.renderCurrent()

}

