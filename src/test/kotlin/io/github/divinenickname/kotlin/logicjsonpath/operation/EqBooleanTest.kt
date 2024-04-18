package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EqBooleanTest {
    @Test
    fun result_isTrue() {
        val obj = Eq("true", "true")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = Eq("true", "false")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
