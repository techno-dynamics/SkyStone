package io.arct.ftccore.gamepad

/**
 * Represents a DPad
 *
 * @property up Up Button
 * @property down Down Button
 * @property left Left Button
 * @property right Right Button
 *
 * @see Gamepad
 */
class DPad internal constructor(
        val up: Boolean,
        val down: Boolean,
        val left: Boolean,
        val right: Boolean
)
