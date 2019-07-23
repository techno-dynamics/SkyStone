package io.arct.ftccore.gamepad

/**
 * Represents a Gamepad
 *
 * @property left Left Joystick
 * @property right Right Joystick
 * @property dpad DPad
 * @property a A Button
 * @property b B Button
 * @property x X Button
 * @property y Y Button
 * @property lb Left Bumper
 * @property rb Right Bumper
 * @property lt Right Trigger
 * @property rt Right Trigger
 * @property back Back Button
 * @property guide Guide Button
 * @property start Start Button
 *
 * @see DPad
 * @see Joystick
 */
data class Gamepad internal constructor(
        val left: Joystick,
        val right: Joystick,

        val dpad: DPad,

        val a: Boolean,
        val b: Boolean,
        val x: Boolean,
        val y: Boolean,

        val lb: Boolean,
        val rb: Boolean,

        val lt: Float,
        val rt: Float,

        val back: Boolean,
        val guide: Boolean,
        val start: Boolean
    ) {

    companion object {

        /**
         * Create a Gamepad from a RobotCore Gamepad
         *
         * @param g RobotCore Gamepad
         *
         * @return Gamepad instance
         */
        fun from(g: com.qualcomm.robotcore.hardware.Gamepad): Gamepad {
            return Gamepad(
                    Joystick(g.left_stick_x, g.left_stick_y, g.left_stick_button),
                    Joystick(g.right_stick_x, g.right_stick_y, g.right_stick_button),

                    DPad(g.dpad_up, g.dpad_down, g.dpad_left, g.dpad_right),

                    g.a, g.b, g.x, g.y,

                    g.left_bumper, g.right_bumper,
                    g.left_trigger, g.right_trigger,

                    g.back, g.guide, g.start
            )
        }
    }
}
