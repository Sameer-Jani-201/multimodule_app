package com.bookmyshow.domain_layer.utils

/**
 * This class is defining SortModes
 */
sealed class SortedMode(val value: String) {
    object NAME : SortedMode(Companion.NAME)
    object DATE : SortedMode(Companion.DATE)
    companion object {
        private const val NAME = "NAME"
        private const val DATE = "DATE"
    }
}