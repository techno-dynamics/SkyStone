package io.arct.ftccore.telemetry

import com.qualcomm.robotcore.eventloop.opmode.OpMode

/**
 * Sends information to the Driver Station
 *
 * @see io.arct.ftccore.robot.Robot
 */
class Telemetry internal constructor(opMode: OpMode) {
    private val telemetry = opMode.telemetry

    /**
     * Automatic log clearing on [Telemetry.update]
     */
    var autoClear: Boolean
        get() = telemetry.isAutoClear
        set(v) { telemetry.isAutoClear = v }

    /**
     * Add a line to telemetry
     *
     * @param line The line to add
     * @return The telemetry instance
     */
    fun add(line: String): Telemetry {
        telemetry.addLine(line)

        return this
    }

    /**
     * Add lines to telemetry
     *
     * @param data A [List] of lines to add
     * @return The [Telemetry] instance
     */
    fun add(data: List<String>): Telemetry {
        for (line in data)
            telemetry.addLine(line)

        return this
    }

    /**
     * Update/Send queued telemetry data
     */
    fun update() {
        telemetry.update()
    }
}
