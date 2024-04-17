package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NotEqTest {
    @Test
    fun result_isTrue() {
        val obj = NotEq("1", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = NotEq("1", "1")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
