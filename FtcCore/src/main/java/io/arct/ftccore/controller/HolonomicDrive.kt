package io.arct.ftccore.controller

import io.arct.ftccore.device.Motor
import io.arct.ftccore.extentions.round
import io.arct.ftccore.gamepad.Gamepad
import io.arct.ftccore.robot.Robot
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * A drive controller for a robot using holonomic drive
 *
 * @param robot The robot this controller is controlling
 * @param motors A [List] of motors in the holonomic drive array
 */
class HolonomicDrive(robot: Robot, motors: List<String>) : DriveController(robot) {
    private val lfm: Motor = robot.map.motor(motors[0])
    private val rfm: Motor = robot.map.motor(motors[1])
    private val lbm: Motor = robot.map.motor(motors[2])
    private val rbm: Motor = robot.map.motor(motors[3])

    init {
        lfm.zeroPower = Motor.ZeroPowerBehavior.BRAKE
        rfm.zeroPower = Motor.ZeroPowerBehavior.BRAKE
        lbm.zeroPower = Motor.ZeroPowerBehavior.BRAKE
        rbm.zeroPower = Motor.ZeroPowerBehavior.BRAKE
    }

    /**
     * @see DriveController
     */
    override fun move(direction: Double, power: Double, distance: Double) {
        val x = sin(direction * PI / 180).round(14)
        val y = cos(direction * PI / 180).round(14)

        lfm.move((+y + x) * power, distance * distanceConstant, false)
        rfm.move((-y + x) * power, distance * distanceConstant, false)
        lbm.move((+y - x) * power, distance * distanceConstant, false)
        rbm.move((-y - x) * power, distance * distanceConstant, false)

        while (lfm.busy || rfm.busy || lbm.busy || rbm.busy)
            Thread.sleep(1)
    }

    /**
     * @see DriveController
     */
    override fun rotate(power: Double, distance: Double) {
        lfm.move(power, distance * rotationConstant, false)
        rfm.move(power, distance * rotationConstant, false)
        lbm.move(power, distance * rotationConstant, false)
        rbm.move(power, distance * rotationConstant, false)

        while (lfm.busy || rfm.busy || lbm.busy || rbm.busy)
            Thread.sleep(1)
    }

    /**
     * @see DriveController
     */
    override fun use(gamepad: Gamepad) {
        val x = gamepad.left.x
        val y = gamepad.left.y
        val r = gamepad.right.x

        lfm.power((+y + x + r).toDouble())
        rfm.power((-y + x + r).toDouble())
        lbm.power((+y - x + r).toDouble())
        rbm.power((-y - x + r).toDouble())
    }

    companion object {
        /**
         * The ratio between a holonomic drive unit and a centimeter
         */
        var distanceConstant = 1.0

        /**
         * The ratio between a golonomic drive rotation unit and a degree
         */
        var rotationConstant = 1.0
    }
}
