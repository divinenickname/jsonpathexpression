package io.github.divinenickname.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StringFunctionsTest {

    @ParameterizedTest
    @ValueSource(strings = ["true", "TRUE", "tRuE", "false", "FALSE", "FaLsE"])
    fun isBoolean_isTrue(string: String) {
        Assertions.assertTrue(string.isBoolean())
    }

    @Test
    fun isBoolean_isFalse() {
        Assertions.assertFalse("notTrue".isBoolean())
    }

    @Test
    fun isLong_isTrue() {
        Assertions.assertTrue(Long.MAX_VALUE.toString().isLong())
    }

    @Test
    fun isLong_isFalse() {
        Assertions.assertFalse("abc".isLong())
    }

    @Test
    fun isInt_isTrue() {
        Assertions.assertTrue(Int.MAX_VALUE.toString().isInt())
    }

    @Test
    fun isInt_isFalse() {
        Assertions.assertFalse("abc".isInt())
    }

    @Test
    fun isDouble_isTrue() {
        Assertions.assertTrue(Double.MAX_VALUE.toString().isDouble())
    }

    @Test
    fun isDouble_isFalse() {
        Assertions.assertFalse("abc".isDouble())
    }

    @Test
    fun isFloat_isTrue() {
        Assertions.assertTrue(Float.MAX_VALUE.toString().isFloat())
    }

    @Test
    fun isFloat_isFalse() {
        Assertions.assertFalse("abc".isFloat())
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            Int.MAX_VALUE.toString(),
            Long.MAX_VALUE.toString(),
            Double.MAX_VALUE.toString(),
            Float.MAX_VALUE.toString(),
        ]
    )
    fun isNumber_isTrue(string: String) {
        Assertions.assertTrue(string.isFloat())
    }

    @Test
    fun isNumber_isFalse() {
        Assertions.assertFalse("1a".isFloat())
    }

}
