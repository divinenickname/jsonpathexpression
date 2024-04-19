package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException

internal class GreaterThanOrEqDoubleTest {

    @Test
    fun result_isTrue() {
        val obj = GreaterThanOrEq("2.001", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_equals_isTrue() {
        val obj = GreaterThanOrEq("2.00001", "2.00001")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = GreaterThanOrEq("2.00001", "2.00002")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
