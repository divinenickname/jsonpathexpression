package io.github.divinenickname.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ExpressionTest {

    private val exp = "\$.selected.srcProduct.currencyCode\$.selected.dstProduct.currencyCode\$="

    @Test
    fun tokensTest() {
        val obj = Expression(exp)

        val expected = ArrayDeque<Token>().apply {
            addLast(Token("\$.selected.srcProduct.currencyCode"))
            addLast(Token("\$.selected.dstProduct.currencyCode"))
            addLast(Token("="))
        }
        val actual = obj.tokens()

        Assertions.assertEquals(expected, actual)
    }
}
