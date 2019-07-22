package io.arct.ftccore.robot

import com.qualcomm.robotcore.eventloop.opmode.OpMode

import io.arct.ftccore.gamepad.Gamepad

/**
 * A hardware robot
 *
 * @property map A [HardwareMap] of connected devices
 * @property gamepad The [Gamepad]s linked to the robot
 */
class Robot internal constructor(private val opMode: OpMode) {
    var map: HardwareMap = HardwareMap(opMode)
    val gamepad: List<Gamepad>
        get() = listOf(Gamepad.from(opMode.gamepad1), Gamepad.from(opMode.gamepad2))
}
