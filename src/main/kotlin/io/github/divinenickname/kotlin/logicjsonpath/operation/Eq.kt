package io.github.divinenickname.kotlin.logicjsonpath.operation

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

    override fun result(): Boolean = operand1.toBoolean() && operand2.toBoolean()
}

class Or(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean = operand1.toBoolean() || operand2.toBoolean()
}

class LessThan(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean = operand1.toLong() < operand2.toLong()
}

class LessThanOrEq(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean = operand1.toLong() < operand2.toLong()
}

class GreaterThan(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean = operand1.toLong() > operand2.toLong()
}

class GreaterThanOrEq(
    private val operand1: String,
    private val operand2: String,
) : Operation(operand1, operand2) {

    override fun result(): Boolean = operand1.toLong() > operand2.toLong()
}
