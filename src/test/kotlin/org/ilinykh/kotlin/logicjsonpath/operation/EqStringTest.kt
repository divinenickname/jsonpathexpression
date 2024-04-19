package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EqStringTest {
    @Test
    fun result_isTrue() {
        val obj = Eq("abc", "abc")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = Eq("abc", "123")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
