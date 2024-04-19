package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class AndTest {

    @Test
    fun result_isTrue() {
        val obj = And("true", "true")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = And("true", "false")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_nonBooleanArg1_isThrow() {
        val obj = And("1", "false")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }

    @Test
    fun result_nonBooleanArg2_isThrow() {
        val obj = And("true", "1")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }
}
