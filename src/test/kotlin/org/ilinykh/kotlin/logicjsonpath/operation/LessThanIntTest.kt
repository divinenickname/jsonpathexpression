package org.ilinykh.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException

internal class LessThanIntTest {

    @Test
    fun result_isTrue() {
        val obj = LessThan("1", "2")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_equals_isFalse() {
        val obj = LessThan("2", "2")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = LessThan("2", "1")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_notIntegerArg1_throw() {
        val obj = LessThan("1a", "2")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }

    @Test
    fun result_notIntegerArg2_throw() {
        val obj = LessThan("1", "a2")

        Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }
    }
}
