package io.arct.ftccore

import com.qualcomm.robotcore.eventloop.opmode.OpMode

import io.arct.ftccore.robot.Robot
import io.arct.ftccore.telemetry.Telemetry


/**
 * Represents an operation mode.
 *
 * An operation mode is a program that can be executed on the robot.
 *
 * Tag a subclass of OperationMode with either [com.qualcomm.robotcore.eventloop.opmode.TeleOp] or [com.qualcomm.robotcore.eventloop.opmode.Autonomous] to specify the category of this operation mode
 *
 * @property robot The [Robot] associated with this operation mode
 * @property log The [Telemetry] instance of this operation mode
 *
 * @see LinearOperationMode
 */
abstract class OperationMode : OpMode() {
    val robot = Robot(this)
    val log = Telemetry(this)
}
