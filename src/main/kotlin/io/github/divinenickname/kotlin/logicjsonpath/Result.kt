package io.github.divinenickname.kotlin.logicjsonpath

import com.jayway.jsonpath.JsonPath
import io.github.divinenickname.kotlin.logicjsonpath.operation.Operations

/**
 * Result of logical operation.
 * @param json source json string
 * @param exp logical expression
 */
class Result(
    private val json: String,
    private val exp: Expression
) {

    /**
     * @return logical result of expression
     */
    fun result(): Boolean {
        val deque = exp.tokens()
        val acc = ArrayDeque<String>()

        exp.tokens().forEach {
            if (it.isJsonPath()) {
                JsonPath.read<Any>(json, deque.removeFirst().toString()).toString().let(acc::addLast)
            }
            else if (it.isOperator() || acc.size == 2) {
                val operand2 = acc.removeLast()
                val operand1 = acc.removeLast()
                deque.removeFirst()

                Operations.ops[it.toString()]
                    ?.let { constructor -> constructor(operand1, operand2) }
                    ?.result()?.toString()?.apply { acc.addLast(this) }
                    ?: throw RuntimeException("This '$it' operation in not supported")
            }
            else {
                acc.addLast(deque.removeFirst().toString())
            }
        }

        return acc.removeFirst() == "true"
    }
}
