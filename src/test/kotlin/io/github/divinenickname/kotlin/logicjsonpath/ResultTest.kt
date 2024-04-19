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

    @Test
    fun result_unsupportedOperator_throws() {
        val json = """
            {
              "payload": {
                "first": {
                  "value": true
                },
                "second": {
                  "value": true
                }
              }
            }
        """.trimIndent()
        val str = "#\$.payload.first.value#\$.payload.second.value#{}"

        Assertions.assertThrows(RuntimeException::class.java) {
            Result(json, str.let(::Expression)).result()
        }.also {
            Assertions.assertEquals("This '{}' operation in not supported" ,it.message)
        }
    }

    @Test
    fun result_sum_success() {
        val json = """
            {
                "store": {
                    "book": [
                        {
                            "category": "reference",
                            "author": "Nigel Rees",
                            "title": "Sayings of the Century",
                            "price": 8.95
                        },
                        {
                            "category": "fiction",
                            "author": "Evelyn Waugh",
                            "title": "Sword of Honour",
                            "price": 12.99
                        },
                        {
                            "category": "fiction",
                            "author": "Herman Melville",
                            "title": "Moby Dick",
                            "isbn": "0-553-21311-3",
                            "price": 8.99
                        },
                        {
                            "category": "fiction",
                            "author": "J. R. R. Tolkien",
                            "title": "The Lord of the Rings",
                            "isbn": "0-395-19395-8",
                            "price": 22.99
                        }
                    ],
                    "bicycle": {
                        "color": "red",
                        "price": 19.95
                    }
                },
                "expensive": 10
            }
        """.trimIndent()

        val actual = Result(json, Expression("#\$.sum(\$.store.book[*].price)#100#>")).result()

        Assertions.assertFalse(actual)
    }
}
