package io.arct.ftccore.extentions

import kotlin.math.round

/**
 * Rounds a double to N decimal places
 *
 * @param decimals Amount of decimal places to round to
 *
 * @return The rounded decimal
 */
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}