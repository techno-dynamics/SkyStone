package io.arct.ftccore.controller

import io.arct.ftccore.device.Motor
import io.arct.ftccore.gamepad.Gamepad
import io.arct.ftccore.robot.Robot
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

class MecanumDrive(robot: Robot, motors: List<String>) : DriveController(robot) {
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

    override fun move(direction: Double, power: Double, distance: Double) {

    }

    override fun rotate(power: Double, distance: Double) {

    }

    override fun use(gamepad: Gamepad) {
        val x = gamepad.right.x
        val r = hypot(gamepad.right.x, -gamepad.right.y)
        val angle = atan2(-gamepad.left.y, gamepad.left.x) - Math.PI / 4

        lfm.power(r * cos(angle) + x)
        rfm.power(r * sin(angle) - x)
        lbm.power(r * sin(angle) + x)
        rbm.power(r * cos(angle) - x)
    }
}