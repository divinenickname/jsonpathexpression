package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class PlusTest {
    @Test
    fun result_op1_scale3() {
        val obj = Plus("8.956", "1.24")

        val expected = BigDecimal("10.196")
        val actual = obj.result()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun result_op2_scale3() {
        val obj = Plus("8.95", "1.246")

        val expected = BigDecimal("10.196")
        val actual = obj.result()

        Assertions.assertEquals(expected, actual)
    }
}
