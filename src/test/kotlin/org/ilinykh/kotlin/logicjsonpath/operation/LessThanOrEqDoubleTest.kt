package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException

internal class LessThanOrEqDoubleTest {

    @Test
    fun result_isTrue() {
        val obj = LessThanOrEq("1.9991", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_equals_isTrue() {
        val obj = LessThanOrEq("2.00001", "2.00001")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = LessThanOrEq("2.00002", "2.00001")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
