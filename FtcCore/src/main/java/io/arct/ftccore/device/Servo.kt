package io.arct.ftccore.device

/**
 * A hardware servo
 */
class Servo internal constructor(private val servo: com.qualcomm.robotcore.hardware.Servo) : Device(servo) {

    /**
     * Move the servo to a position
     *
     * @param position Position to move to
     */
    fun move(position: Double) {
        servo.position = position
    }
}
