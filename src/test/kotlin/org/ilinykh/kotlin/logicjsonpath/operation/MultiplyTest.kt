package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class MultiplyTest {
    @Test
    fun result_op1_scale3() {
        val obj = Multiply("8.956", "1.24")

        val expected = BigDecimal("11.105")
        val actual = obj.result()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun result_op2_scale3() {
        val obj = Multiply("8.95", "1.246")

        val expected = BigDecimal("11.152")
        val actual = obj.result()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun op1_notNumber_throw() {
        val obj = Multiply("8.95a", "1.246")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }

    @Test
    fun op2_notNumber_throw() {
        val obj = Multiply("8.95", "1.246a")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }
}
