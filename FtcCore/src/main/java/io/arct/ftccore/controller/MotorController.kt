package io.arct.ftccore.controller

import io.arct.ftccore.device.Motor
import io.arct.ftccore.gamepad.Gamepad
import io.arct.ftccore.robot.Robot
import java.util.*

/**
 * A controller that controls an array of motors
 */
class MotorController : Controller {
    private val motors = ArrayList<Motor>()

    /**
     * Initialize a [MotorController]
     *
     * @param robot Robot this controller controls
     * @param motors An [Array] of motor identifiers to control
     */
    constructor(robot: Robot, motors: Array<String>) : super(robot) {

        for (motor in motors)
            this.motors.add(robot.map.motor(motor))
    }

    /**
     * Initialize a [MotorController]
     *
     * @param robot Robot this controller controls
     * @param motors An [Array] of motors to control
     */
    constructor(robot: Robot, motors: Array<Motor>) : super(robot) {
        this.motors.addAll(Arrays.asList(*motors))
    }

    /**
     * Move all the motors a specific distance
     *
     * @param power The power to apply to the motors
     * @param distance The distance to move the motors
     * @param wait Whether to wait for the motors to complete or not
     */
    fun move(power: Double, distance: Double, wait: Boolean = true) {
        for (motor in motors)
            motor.move(power, distance, false)

        if (!wait)
            return

        for (motor in motors)
            while (motor.busy)
                Thread.sleep(1)
    }

    /**
     * Set the power of all the motors
     *
     * @param power The power to apply
     */
    fun power(power: Double) {
        for (motor in motors)
            motor.power(power)
    }

    /**
     * Add a motor
     *
     * @param identifier Identifier of the motor to add
     */
    fun add(identifier: String) {
        motors.add(robot.map.motor(identifier))
    }

    /**
     * Add a motor
     *
     * @param motor Motor to add
     */
    fun add(motor: Motor) {
        motors.add(motor)
    }

    /**
     * Remove a motor
     *
     * @param motor Motor to remove
     */
    fun remove(motor: Motor) {
        motors.remove(motor)
    }

    /**
     * Remove all motors
     */
    fun clear() {
        motors.clear()
    }

    /**
     * A [List] of all of the motors
     */
    fun motors(): List<Motor> {
        return motors
    }

    /**
     * @suppress
     */
    override fun use(gamepad: Gamepad) {}
}
