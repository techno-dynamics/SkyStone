package io.arct.ftccore.device

import com.qualcomm.robotcore.hardware.DcMotor

/**
 * A hardware DC Motor
 *
 * @property position The current position of the motor
 * @property busy Is the motor currently busy
 * @property zeroPower The [ZeroPowerBehavior] of the motor when a power of zero is applied
 */
class Motor internal constructor(private var m: DcMotor) : Device(m) {
    val position
        get() = m.currentPosition

    val busy
        get() = m.isBusy

    var zeroPower
        get() = when (m.zeroPowerBehavior) {
            DcMotor.ZeroPowerBehavior.BRAKE -> ZeroPowerBehavior.BRAKE
            DcMotor.ZeroPowerBehavior.FLOAT -> ZeroPowerBehavior.COAST
            else -> ZeroPowerBehavior.UNKNOWN
        }
        set(v) = when (v) {
            ZeroPowerBehavior.BRAKE -> m.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
            ZeroPowerBehavior.COAST -> m.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
            else -> m.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.UNKNOWN
        }

    /**
     * Set a particular power value to the motor
     *
     * @param power The power to set
     */
    fun power(power: Double) {
        m.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER

        m.power = power
    }

    /**
     * Move the motor to a position
     *
     * @param power The power to set
     * @param position The position to move to
     * @param wait Wait for the motor to reach the specified position
     */
    fun move(power: Double, position: Double, wait: Boolean = true) {
        m.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        m.targetPosition = (position * distanceConstant).toInt()

        m.mode = DcMotor.RunMode.RUN_TO_POSITION

        m.power = power

        if (!wait)
            return

        while (m.isBusy)
            Thread.sleep(1)

        stop()
    }

    /**
     * Stop the motor
     */
    fun stop() {
        m.power = 0.0
    }

    /**
     * The behavior of a motor when a power of zero is applied
     */
    enum class ZeroPowerBehavior {
        /**
         * Let the motor coast and do not attempt to brake
         */
        COAST,

        /**
         * Apply brake
         */
        BRAKE,

        /**
         * Behavior is unknown
         */
        UNKNOWN
    }

    companion object {
        /**
         * The ratio between a motor encoder step and a degree
         */
        var distanceConstant = 4.0
    }
}
