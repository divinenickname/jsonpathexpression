package io.github.divinenickname.kotlin.logicjsonpath.operation

import io.github.divinenickname.kotlin.logicjsonpath.isBoolean
import io.github.divinenickname.kotlin.logicjsonpath.isNumber
import java.math.BigDecimal

class Eq(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean = operand1 == operand2
}

class NotEq(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean = operand1 != operand2
}

class And(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean {
        require(operand1.isBoolean())
        require(operand2.isBoolean())

        return operand1.toBoolean() && operand2.toBoolean()
    }
}

class Or(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean {
        require(operand1.isBoolean())
        require(operand2.isBoolean())

        return operand1.toBoolean() || operand2.toBoolean()
    }
}

class LessThan(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean {
        require(operand1.isNumber())
        require(operand2.isNumber())

        return BigDecimal(operand1) < BigDecimal(operand2)
    }
}

class LessThanOrEq(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean {
        require(operand1.isNumber())
        require(operand2.isNumber())

        return BigDecimal(operand1) <= BigDecimal(operand2)
    }
}

class GreaterThan(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean {
        require(operand1.isNumber())
        require(operand2.isNumber())

        return BigDecimal(operand1) > BigDecimal(operand2)
    }
}

class GreaterThanOrEq(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean {
        require(operand1.isNumber())
        require(operand2.isNumber())

        return BigDecimal(operand1) >= BigDecimal(operand2)
    }
}
