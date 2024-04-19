package org.ilinykh.kotlin.logicjsonpath

/**
 * Wrapper for string parsed token
 * @param str string parsed token
 */
data class Token(private val str: String) {

    companion object {
        private val operators = setOf("=", "!=", "<", "<=", ">=", ">", "&", "|")
    }

    /**
     * @return true if given string is jsonPath
     */
    fun isJsonPath(): Boolean = str.length > 1 && str[1] == '.'

    fun isOperator():Boolean = str in operators

    override fun toString():String = str
}
