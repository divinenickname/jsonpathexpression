package io.github.divinenickname.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class ResultTest {
    @Test
    fun result_isTrue() {
        val json = File("src/test/resources/stringTrueResult.json").readText()
        val obj = Result(json, Expression("\$.selected.srcProduct.currencyCode\$.selected.dstProduct.currencyCode\$="))

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_isFalse() {
        val json = File("src/test/resources/stringFalseResult.json").readText()
        val obj = Result(json, Expression("\$.selected.srcProduct.currencyCode\$.selected.dstProduct.currencyCode\$="))

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_multiple_isFalse() {
        val json = File("src/test/resources/stringMultipleFalseResult.json").readText()
        val str = "\$.selected.srcProduct.currencyCode\$.selected.dstProduct.currencyCode\$=" +
                "\$.selected.srcProduct.currencyCode\$.selected.extProduct.currencyCode\$=" +
                "\$&"
        val obj = str
            .let(::Expression)
            .let { Result(json, it) }

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_multiple_isTrue() {
        val json = File("src/test/resources/stringMultipleFalseResult.json").readText()
        val str = "\$.selected.srcProduct.currencyCode\$.selected.dstProduct.currencyCode\$=" +
                "\$.selected.srcProduct.currencyCode\$.selected.extProduct.currencyCode\$=" +
                "\$&"
        val obj = str
            .let(::Expression)
            .let { Result(json, it) }

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_int_isFalse() {
        val json = File("src/test/resources/intFalseResult.json").readText()
        val obj = Result(json, Expression("\$.selected.srcProduct.value\$.selected.dstProduct.value\$="))

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_int_isTrue() {
        val json = File("src/test/resources/intFalseResult.json").readText()
        val obj = Result(json, Expression("\$.selected.srcProduct.value\$.selected.dstProduct.value\$<"))

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }
}
