package io.github.divinenickname.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class ResultTest {
    @Test
    fun result_boolean_isTrue() {
        val json = """
            {
              "payload": {
                "first": {
                  "value": true
                },
                "second": {
                  "value": TRUE
                }
              }
            }
        """.trimIndent()
        val obj = Result(json, Expression("\$.payload.first.value\$.payload.second.value\$="))

        val actual = obj.result()

        Assertions.assertTrue(actual)
    }

    @Test
    fun result_boolean_isFalse() {
        val json = """
            {
              "payload": {
                "first": {
                  "value": true
                },
                "second": {
                  "value": false
                }
              }
            }
        """.trimIndent()
        val obj = Result(json, Expression("\$.payload.first.value\$.payload.second.value\$="))

        val actual = obj.result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_booleanAndString_isFalse() {
        val json = """
            {
              "payload": {
                "first": {
                  "value": true
                },
                "second": {
                  "value": string
                }
              }
            }
        """.trimIndent()
        val obj = Result(json, Expression("\$.payload.first.value\$.payload.second.value\$="))

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
