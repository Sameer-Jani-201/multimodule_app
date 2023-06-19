package com.bookmyshow.domain_layer.utils


import java.text.SimpleDateFormat
import java.util.Date

object Converter {

    /**
     * Helper method to covert date from string value.
     * @param stringDate
     * @return date object[Date]
     */
    fun parseStringToDate(stringDate: String): Date? {
        var values = stringDate
        values = values.replace("st", "")
        values = values.replace("nd", "")
        values = values.replace("rd", "")
        values = values.replace("th", "")
        return SimpleDateFormat("d MMMM, yyyy").parse(values)
    }
}