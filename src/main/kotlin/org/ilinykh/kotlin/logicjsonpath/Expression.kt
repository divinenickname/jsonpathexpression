package org.ilinykh.kotlin.logicjsonpath

/**
 * @param exp logical expression based on json path
 */
class Expression(private val exp: String) {

    /**
     * Parse tokens from string
     */
    fun tokens(): ArrayDeque<org.ilinykh.kotlin.logicjsonpath.Token> {
        require(exp.length > 1)
        require(exp.first() == '#')

        val deque = ArrayDeque<org.ilinykh.kotlin.logicjsonpath.Token>()

        exp.split("#")
            .forEach {
                it.takeIf { it.isNotEmpty() }
                    ?.let(::Token)
                    ?.let(deque::addLast)
            }

        return deque
    }
}
