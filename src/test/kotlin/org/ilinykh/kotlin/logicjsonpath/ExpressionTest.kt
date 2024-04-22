package org.ilinykh.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ExpressionTest {

    @Test
    fun tokens_success() {
        val obj = Expression("#\$.payload.first.value#\$.payload.second.value#=")

        val expected = ArrayDeque<Token>().apply {
            addLast(Token("\$.payload.first.value"))
            addLast(Token("\$.payload.second.value"))
            addLast(Token("="))
        }
        val actual = obj.tokens()

        Assertions.assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ValueSource(strings = ["missed'$' symbol at start", "a"])
    fun tokens_invalidExp(exp: String) {
        Assertions.assertThrows(IllegalArgumentException::class.java) { Expression(exp).tokens() }
    }
}
