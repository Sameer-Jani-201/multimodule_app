package com.bookmyshow.domain_layer.model

import java.util.Date

data class VenuesModel(val name: String, val showDate: Date, val list: List<ShowTime>?) {

    constructor(name: String, showDate: Date) : this(name, showDate, null)

    inner class ShowTime(val showTime: String, val showDateCode: String)
}
