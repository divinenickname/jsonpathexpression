package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NotEqStringTest {
    @Test
    fun result_isTrue() {
        val obj = NotEq("abc", "1abc")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = NotEq("abc", "abc")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
