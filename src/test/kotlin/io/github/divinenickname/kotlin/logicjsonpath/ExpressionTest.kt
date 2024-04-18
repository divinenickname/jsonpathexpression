package io.github.divinenickname.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ExpressionTest {

    private val exp = "\$.payload.first.value\$.payload.second.value\$="

    @Test
    fun tokensTest() {
        val obj = Expression(exp)

        val expected = ArrayDeque<Token>().apply {
            addLast(Token("\$.payload.first.value"))
            addLast(Token("\$.payload.second.value"))
            addLast(Token("="))
        }
        val actual = obj.tokens()

        Assertions.assertEquals(expected, actual)
    }
}
