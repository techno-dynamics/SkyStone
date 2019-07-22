package io.arct.ftccore.controller

import io.arct.ftccore.gamepad.Gamepad
import io.arct.ftccore.robot.Robot

/**
 * A controller that controls robot movement
 *
 * @param robot The robot this controller controls
 */
abstract class DriveController(robot: Robot) : Controller(robot) {

    /**
     * @see Controller
     */
    abstract override fun use(gamepad: Gamepad)

    /**
     * Programmatically move the robot
     *
     * @param direction A [Double] representing the direction the robot should move
     * @param power The power of the motors
     * @param distance How far the robot should move
     */
    abstract fun move(direction: Double, power: Double, distance: Double)

    /**
     * Programmatically rotate the robot
     *
     * @param power The power to set the motors at
     * @param distance How many degrees the robot should rotate
     */
    abstract fun rotate(power: Double, distance: Double)
}
