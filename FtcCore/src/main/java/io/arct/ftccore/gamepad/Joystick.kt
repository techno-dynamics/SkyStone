package io.arct.ftccore.gamepad


/**
 * Represents a Joystick
 *
 * @property x X value (-1 to 1)
 * @property y Y value (-1 to 1)
 * @property button Joystick Button
 *
 * @see Gamepad
 */
data class Joystick internal constructor(
        val x: Float,
        val y: Float,
        val button: Boolean
)
