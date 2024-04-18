package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EqIntegerTest {
    @Test
    fun result_isTrue() {
        val obj = Eq("1", "1")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = Eq("1", "2")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
