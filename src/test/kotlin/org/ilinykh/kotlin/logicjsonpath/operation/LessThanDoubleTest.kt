package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException

internal class LessThanDoubleTest {

    @Test
    fun result_isTrue() {
        val obj = LessThan("1.9991", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_equals_isFalse() {
        val obj = LessThan("2.00001", "2.00001")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = LessThan("2.00002", "2.00001")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
