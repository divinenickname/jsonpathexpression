package io.github.divinenickname.kotlin.logicjsonpath

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
        val obj = Result(json, Expression("#\$.payload.first.value#\$.payload.second.value#="))

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
        val obj = Result(json, Expression("#\$.payload.first.value#\$.payload.second.value#="))

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
        val obj = Result(json, Expression("#\$.payload.first.value#\$.payload.second.value#="))

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
        val str = "#\$.payload.first.value#\$.payload.second.value#=" +
                "#\$.payload.first.value#\$.payload.third.value#=" +
                "#&"

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
        val str = "#\$.payload.first.value#\$.payload.second.value#=" +
                "#\$.payload.first.value#\$.payload.third.value#=" +
                "#&"

        val actual = Result(json, str.let(::Expression))
            .result()

        Assertions.assertTrue(actual)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "#\$.sum(\$.store.book[*].price)#100#>",
            "#100#\$.sum(\$.store.book[*].price)#<",
            "#$.sum($.store.book[*].price)#100#>"
        ]
    )
    fun result_sumFun_success(expString: String) {
        val json = """
            {
                "store": {
                    "book": [
                        {
                            "title": "Sayings of the Century",
                            "price": 8.95
                        },
                        {
                            "title": "Sword of Honour",
                            "price": 12.99
                        },
                        {
                            "title": "Moby Dick",
                            "price": 8.99
                        },
                        {
                            "title": "Cipollino",
                            "price": 22.99
                        }
                    ]
                }
            }
        """.trimIndent()

        val actual = Result(json, Expression(expString)).result()

        Assertions.assertFalse(actual)
    }

    @ParameterizedTest
    @ValueSource(strings = ["#12#10#>#12#15#<#=", "#10#6#>"])
    fun result_expWithoutJson_isTrue(expStr: String) {
        val actual = Result(Expression(expStr)).result()

        Assertions.assertTrue(actual)
    }
}
