package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class OrTest {
    @Test
    fun result_boolean_isTrue() {
        val obj = Or("true", "false")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_boolean_isFalse() {
        val obj = Or("false", "false")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
