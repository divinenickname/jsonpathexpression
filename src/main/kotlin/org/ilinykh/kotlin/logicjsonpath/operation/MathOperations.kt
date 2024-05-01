package org.ilinykh.kotlin.logicjsonpath.operation

import org.ilinykh.kotlin.logicjsonpath.isNumber
import java.math.BigDecimal
import java.math.RoundingMode

class Plus(
    private val operand1: String,
    private val operand2: String,
) : Operation<BigDecimal>(operand1, operand2) {

    override fun result(): BigDecimal {
        require(operand1.isNumber())
        require(operand2.isNumber())

        val biggerScale = BiggerScale(BigDecimal(operand1), BigDecimal(operand2)).result()

        return BigDecimal(operand1).plus(BigDecimal(operand2)).setScale(biggerScale)
    }
}

class Minus(
    private val operand1: String,
    private val operand2: String,
) : Operation<BigDecimal>(operand1, operand2) {

    override fun result(): BigDecimal {
        require(operand1.isNumber())
        require(operand2.isNumber())

        val biggerScale = BiggerScale(BigDecimal(operand1), BigDecimal(operand2)).result()

        return BigDecimal(operand1).minus(BigDecimal(operand2)).setScale(biggerScale)
    }
}

class Multiply(
    private val operand1: String,
    private val operand2: String,
) : Operation<BigDecimal>(operand1, operand2) {

    override fun result(): BigDecimal {
        require(operand1.isNumber())
        require(operand2.isNumber())

        val biggerScale = BiggerScale(BigDecimal(operand1), BigDecimal(operand2)).result()

        return BigDecimal(operand1).multiply(BigDecimal(operand2)).setScale(biggerScale, RoundingMode.HALF_UP)
    }
}

class Divide(
    private val operand1: String,
    private val operand2: String,
) : Operation<BigDecimal>(operand1, operand2) {

    override fun result(): BigDecimal {
        require(operand1.isNumber())
        require(operand2.isNumber())

        val biggerScale = BiggerScale(BigDecimal(operand1), BigDecimal(operand2)).result()

        return BigDecimal(operand1).divide(BigDecimal(operand2), biggerScale, RoundingMode.HALF_UP)
    }
}

internal class BiggerScale(
    private val operand1: BigDecimal,
    private val operand2: BigDecimal
) {
    fun result(): Int = if (operand1.scale() > operand2.scale()) {
        operand1
    } else {
        operand2
    }.scale()

}
