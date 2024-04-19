package org.ilinykh.kotlin.logicjsonpath.operation

/**
 * Singleton for logical operations and their expression string reference.
 */
object Operations {
    val ops = mapOf(
        "=" to ::Eq,
        "!=" to ::NotEq,
        "&" to ::And,
        "|" to ::Or,
        "<" to ::LessThan,
        "<=" to ::LessThanOrEq,
        ">" to ::GreaterThan,
        ">=" to ::GreaterThanOrEq,
    )
}
