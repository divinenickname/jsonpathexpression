package io.github.divinenickname.kotlin.logicjsonpath


fun String.isBoolean(): Boolean = setOf("true", "false").contains(this.lowercase())
fun String.isLong() = toLongOrNull() != null
fun String.isInt() = toIntOrNull() != null
fun String.isDouble() = toDoubleOrNull() != null
fun String.isFloat() = toFloatOrNull() != null
fun String.isNumber() = setOf(
    String::isLong,
    String::isInt,
    String::isFloat,
    String::isDouble,
).map { it(this) }
    .count { !it } != 4
