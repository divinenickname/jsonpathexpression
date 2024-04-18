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
        val json = """
            {
              "payload": {
                "first": {
                  "value": true
                },
                "second": {
                  "value": false
                },
                "third": {
                   "value": true
                }
              }
            }
        """.trimIndent()
        val str = "\$.payload.first.value\$.payload.second.value\$=" +
                "\$.payload.first.value\$.payload.third.value\$=" +
                "\$&"

        val actual = Result(json, str.let(::Expression))
            .result()

        Assertions.assertFalse(actual)
    }

    @Test
    fun result_multiple_isTrue() {
        val json = """
            {
              "payload": {
                "first": {
                  "value": true
                },
                "second": {
                  "value": true
                },
                "third": {
                   "value": true
                }
              }
            }
        """.trimIndent()
        val str = "\$.payload.first.value\$.payload.second.value\$=" +
                "\$.payload.first.value\$.payload.third.value\$=" +
                "\$&"

        val actual = Result(json, str.let(::Expression))
            .result()

        Assertions.assertTrue(actual)
    }
}
