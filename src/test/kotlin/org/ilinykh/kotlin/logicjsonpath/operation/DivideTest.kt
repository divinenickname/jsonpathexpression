package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

internal class DivideTest {

    @Test
    fun result_op1_scale3() {
        val obj = Divide("8.956", "1.24")

        val expected = BigDecimal("7.223")
        val actual = obj.result()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun result_op2_scale3() {
        val obj = Divide("8.95", "1.246")

        val expected = BigDecimal("7.183")
        val actual = obj.result()

        Assertions.assertEquals(expected, actual)
    }
}
