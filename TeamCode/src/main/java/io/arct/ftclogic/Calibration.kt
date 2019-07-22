package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import io.arct.ftccore.LinearOperationMode
import io.arct.ftccore.controller.DriveController
import io.arct.ftccore.controller.HolonomicDrive
import io.arct.ftccore.extentions.round

@TeleOp
class Calibration : LinearOperationMode() {
    private var calibratingDistance: Boolean = false
    private var drive: DriveController? = null

    override fun _init() {
        drive = HolonomicDrive(robot, listOf("left-front", "right-front", "left-back", "right-back"))

        log.autoClear = true

        log
                .add("Ready!")
                .update()
    }

    override fun run() {
        while (this.opModeIsActive()) {
            val gamepad = robot.gamepad[0]

            log.add(listOf("Rotation Constant (degrees): ${HolonomicDrive.rotationConstant}", "Distance Constant (cm): ${HolonomicDrive.distanceConstant}", "Currently Calibrating: ${(if (calibratingDistance) "Distance" else "Rotation") + " Constant"}")).update()

            var modifier = 0.0

            when {
                gamepad.lb -> modifier -= 0.01
                gamepad.lt > 0.5 -> modifier -= 0.1
                gamepad.rb -> modifier += 0.01
                gamepad.rt > 0.5 -> modifier += 0.1
                gamepad.dpad.up -> modifier += 1
                gamepad.dpad.down -> modifier -= 1
            }


            if (calibratingDistance)
                HolonomicDrive.distanceConstant += modifier.round(8)
            else
                HolonomicDrive.rotationConstant += modifier.round(8)

            if (modifier != 0.0)
                try {
                    Thread.sleep(250)
                } catch (ignore: Exception) {
                }

            if (gamepad.a) {
                if (calibratingDistance) {
                    log.add(listOf("Calibrating Distance...", "Attempting to move robot by 2 metres.", "Distance Constant (cm): ${HolonomicDrive.distanceConstant}")).update()

                    drive!!.move(0.0, 0.75, 200.0)
                } else {
                    log.add(listOf("Calibrating Rotation...", "Attempting to rotate robot by 16 rotations.", "Rotation Constant (degrees): ${HolonomicDrive.rotationConstant}")).update()

                    drive!!.rotate(0.75, 360 * 16.0)
                }

                log.add(listOf("Completed Calibration Test", "Rotation Constant (degrees): ${HolonomicDrive.rotationConstant}", "Distance Constant (cm): ${HolonomicDrive.distanceConstant}")).update()

                try {
                    Thread.sleep(3000)
                } catch (ignore: Exception) {
                }
            } else if (gamepad.b) {
                calibratingDistance = !calibratingDistance

                try {
                    Thread.sleep(250)
                } catch (ignore: Exception) {
                }
            }
        }
    }
}
