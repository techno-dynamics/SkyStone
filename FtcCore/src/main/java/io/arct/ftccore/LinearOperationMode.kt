package io.arct.ftccore

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import io.arct.ftccore.robot.Robot
import io.arct.ftccore.telemetry.Telemetry

/**
 * Represents an linear operation mode.
 *
 * @property robot The [Robot] associated with this operation mode
 * @property log The [Telemetry] instance of this operation mode
 *
 * @see OperationMode
 */
abstract class LinearOperationMode : LinearOpMode() {
    val robot = Robot(this)
    val log = Telemetry(this)


    /**
     * @supress
     */
    override fun runOpMode() {
        _init()

        waitForStart()

        run()
    }

    /**
     * Code to execute when the operation mode is initiated
     */
    abstract fun _init()

    /**
     * Code to execute when the operation mode is run
     */
    abstract fun run()
}
