package io.github.divinenickname.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class TokenTest {

    @ParameterizedTest
    @ValueSource(strings = ["\$.abcd", "$.abcd"])
    fun isJsonPath_isTrue(jsonPath: String) {
        val obj = Token(jsonPath)

        val actual = obj.isJsonPath()

        Assertions.assertTrue(actual)
    }

    @Test
    fun isJsonPath_isFalse() {
        val obj = Token(".abcd")

        val actual = obj.isJsonPath()

        Assertions.assertFalse(actual)
    }

    @ParameterizedTest
    @ValueSource(strings = ["=", "!=", "<", "<=", ">=", ">", "&", "|"])
    fun isOperator_isTrue(operator: String) {
        val obj = Token(operator)

        val actual = obj.isOperator()

        Assertions.assertTrue(actual)
    }

    @Test
    fun isOperator_isFalse() {
        val obj = Token("!<")

        val actual = obj.isOperator()

        Assertions.assertFalse(actual)
    }

    @Test
    fun toStringTest() {
        val obj = Token("ABC")

        val expected = "ABC"
        val actual = obj.toString()

        Assertions.assertEquals(expected, actual)
    }
}
