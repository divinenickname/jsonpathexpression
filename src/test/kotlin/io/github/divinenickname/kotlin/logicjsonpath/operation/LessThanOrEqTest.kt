package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class LessThanOrEqTest {
    @Test
    fun result_isTrue() {
        val obj = LessThanOrEq("1", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_equals_isTrue() {
        val obj = LessThanOrEq("2", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = LessThanOrEq("2", "1")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
