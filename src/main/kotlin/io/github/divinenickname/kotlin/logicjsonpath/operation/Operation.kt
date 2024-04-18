package io.github.divinenickname.kotlin.logicjsonpath.operation

/**
 * All operations must be inherit this class.
 */
abstract class Operation(
    private val operand1: String,
    private val operand2: String,
) {
    /**
     * @return result of logical operation
     */
    abstract fun result(): Boolean
}
