@file:Suppress("unused")

package chatt.fullstack.frontend

import chatt.fullstack.frontend.framework.Pages
import chatt.fullstack.frontend.page.*

fun main(args: Array<String>) {

    Pages.register(index)
    Pages.register(createPost)
    Pages.register(viewPost)

    Pages.refresh()

}

