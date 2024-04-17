package io.github.divinenickname.kotlin.logicjsonpath

/**
 * Wrapper for string parsed token
 * @param str string parsed token
 */
data class Token(private val str: String) {

    /**
     * @return true if given string is jsonPath
     */
    fun isJsonPath() = str.length > 1 && str[1] == '.'

    override fun toString() = str
}
