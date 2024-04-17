package io.github.divinenickname.kotlin.logicjsonpath.operation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EqTest {
    @Test
    fun result_string_isTrue() {
        val obj = Eq("abc", "abc")

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_string_isFalse() {
        val obj = Eq("abc", "abcd")

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }
}
