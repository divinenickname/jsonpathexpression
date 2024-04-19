package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NotEqDoubleTest {

    @Test
    fun result_isTrue() {
        val obj = NotEq("1.254111", "1.254112")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = NotEq("1.254111", "1.254111")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
