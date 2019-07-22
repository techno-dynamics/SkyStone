package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import io.arct.ftccore.LinearOperationMode
import io.arct.ftccore.device.Motor

@TeleOp
class MotorCalibration : LinearOperationMode() {
    private var motor: Motor? = null

    override fun _init() {
        motor = robot.map.motor("motor")

        log.autoClear = true

        log
                .add("Ready!")
                .update()
    }

    override fun run() {
        while (this.opModeIsActive()) {
            val gamepad = robot.gamepad[0]

            log.add(listOf("Rotation Constant (degrees): ${Motor.distanceConstant}", "Encoder Value: ${motor!!.position}")).update()

            var modifier = 0.0

            when {
                gamepad.lb -> modifier -= 0.01
                gamepad.lt > 0.5 -> modifier -= 0.1
                gamepad.rb -> modifier += 0.01
                gamepad.rt > 0.5 -> modifier += 0.1
            }

            Motor.distanceConstant += modifier

            if (modifier != 0.0)
                try {
                    Thread.sleep(250)
                } catch (ignore: Exception) {
                }

            if (gamepad.a) {
                log.add(listOf("Calibrating...", "Attempting to rotate motor by 16 rotations.", "Rotation Constant (degrees): ${Motor.distanceConstant}")).update()

                motor!!.move(0.75, (360 * 16).toDouble())

                log.add(listOf("Completed Calibration Test", "Rotation Constant (degrees): ${Motor.distanceConstant}")).update()

                try {
                    Thread.sleep(3000)
                } catch (ignore: Exception) {
                }
            }
        }
    }
}
