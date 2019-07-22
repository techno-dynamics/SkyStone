package io.arct.ftccore.controller

import io.arct.ftccore.gamepad.Gamepad
import io.arct.ftccore.robot.Robot

/**
 * A controller responsible for controlling a part of a robot
 *
 * @param robot The robot this controller controls
 */
abstract class Controller(protected var robot: Robot) {

    /**
     * Code to execute on every iteration (OperationMode only)
     *
     * @param gamepad A gamepad instance
     */
    abstract fun use(gamepad: Gamepad)
}
