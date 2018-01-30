@file:Suppress("unused")

package chatt.fullstack.frontend

import chatt.fullstack.frontend.framework.Pages
import chatt.fullstack.frontend.page.createPost
import chatt.fullstack.frontend.page.index
import chatt.fullstack.frontend.page.viewPost

fun main(args: Array<String>) {

    Pages.register(index)
    Pages.register(createPost)
    Pages.register(viewPost)

    Pages.renderCurrent()

}

