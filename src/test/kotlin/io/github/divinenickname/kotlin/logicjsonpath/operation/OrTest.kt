package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class OrTest {
    @Test
    fun result_isTrue() {
        val obj = Or("true", "false")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = Or("false", "false")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_notBooleanArg1_isFalse() {
        val obj = Or("123", "false")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }

    @Test
    fun result_notBooleanArg2_isFalse() {
        val obj = Or("false", "123")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }
}
