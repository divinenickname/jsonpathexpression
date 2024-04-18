package io.github.divinenickname.kotlin.logicjsonpath

import io.github.divinenickname.kotlin.logicjsonpath.Operators.operators

/**
 * @param exp logical expression based on json path
 */
class Expression(private val exp: String) {

    /**
     * Parse tokens from string
     */
    fun tokens(): ArrayDeque<Token> {
        require(exp.length > 1)
        require(exp.first() == '$')

        val deque = ArrayDeque<Token>()

        exp.split("\$")
            .forEach {
                val element = it.takeIf { it in operators || it.isEmpty() || it[0] != '.' } ?: it.let { "\$${it}" }
                Token(element).let(deque::addLast)
            }

        deque.removeFirst()

        return deque
    }
}
