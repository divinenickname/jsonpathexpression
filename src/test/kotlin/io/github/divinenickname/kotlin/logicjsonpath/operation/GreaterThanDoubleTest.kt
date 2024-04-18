package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GreaterThanDoubleTest {

    @Test
    fun result_isTrue() {
        val obj = GreaterThan("2.001", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_equals_isFalse() {
        val obj = GreaterThan("2.00001", "2.00001")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = GreaterThan("2.00001", "2.00002")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
