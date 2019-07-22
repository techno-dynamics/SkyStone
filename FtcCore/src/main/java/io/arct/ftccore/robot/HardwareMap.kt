package io.arct.ftccore.robot

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import io.arct.ftccore.device.ContinuousServo
import io.arct.ftccore.device.Motor
import io.arct.ftccore.device.Servo

/**
 * A map of hardware devices
 *
 * @see Robot
 */
class HardwareMap internal constructor(private val opMode: OpMode) {

    /**
     * Get a [Motor]
     *
     * @param identifier [Motor] Identifier
     *
     * @return [Motor] instance
     */
    fun motor(identifier: String): Motor {
        return Motor(get(DcMotor::class.java, identifier))
    }

    /**
     * Get a [Servo]
     *
     * @param identifier [Servo] Identifier
     *
     * @return [Servo] instance
     */
    fun servo(identifier: String): Servo {
        return Servo(get(com.qualcomm.robotcore.hardware.Servo::class.java, identifier))
    }

    /**
     * Get a [ContinuousServo]
     *
     * @param identifier [ContinuousServo] Identifier
     *
     * @return [ContinuousServo] Servo instance
     */
    fun continuousServo(identifier: String): ContinuousServo {
        return ContinuousServo(get(CRServo::class.java, identifier))
    }


    /**
     * Get a hardware device
     *
     * @param type Class of device type
     * @param identifier Identifier of the device
     *
     * @return The device
     */
    operator fun <T> get(type: Class<out T>, identifier: String): T {
        return opMode.hardwareMap.get(type, identifier)
    }
}
