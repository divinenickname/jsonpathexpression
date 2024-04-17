package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class AndTest {
    @Test
    fun result_boolean_isTrue() {
        val obj = And("true", "true")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_boolean_isFalse() {
        val obj = And("true", "false")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
