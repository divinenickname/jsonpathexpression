package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EqDoubleTest {
    @Test
    fun result_isTrue() {
        val obj = Eq("0.25", "0.25")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val obj = Eq("0.25", "0.255")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
