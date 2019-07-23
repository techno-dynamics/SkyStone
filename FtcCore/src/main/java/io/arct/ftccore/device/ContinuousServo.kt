package io.arct.ftccore.device

import com.qualcomm.robotcore.hardware.CRServo

/**
 * A hardware continuous rotation servo
 */
class ContinuousServo internal constructor(private val servo: CRServo) : Device(servo) {

    /**
     * Apply power to the servo
     *
     * @param power The power value to apply
     */
    fun power(power: Double) {
        servo.power = power
    }

    /**
     * Move the servo a particular distance
     *
     * @param power The power to apply
     * @param distance The distance to move the continuous servo
     */
    fun move(power: Double, distance: Double) {
        val port = servo.portNumber

        servo.controller.setServoPosition(port, servo.controller.getServoPosition(port) + distance)
    }
}
