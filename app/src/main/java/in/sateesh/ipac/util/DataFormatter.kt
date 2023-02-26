package `in`.sateesh.ipac.util

import java.text.SimpleDateFormat
import java.util.*

object DataFormatter {

    fun parseDate(stringDate:String?):String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yy hh:mm a")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC+5:30")
        val date = inputFormat.parse(stringDate)
        return outputFormat.format(date)
    }
}