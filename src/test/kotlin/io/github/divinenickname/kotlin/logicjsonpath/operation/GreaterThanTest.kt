package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GreaterThanTest {
    @Test
    fun result_isTrue() {
        val obj = GreaterThan("2", "1")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = GreaterThan("1", "2")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_equals_isFalse() {
        val obj = GreaterThan("2", "2")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
