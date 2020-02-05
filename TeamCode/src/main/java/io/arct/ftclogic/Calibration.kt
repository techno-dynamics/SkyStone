package io.arct.ftclogic

import io.arct.ftclib.drive.HolonomicDrive
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.robotlib.drive.Drive
import io.arct.robotlib.extensions.round
import io.arct.robotlib.navigation.Direction
import io.arct.robotlib.robot.device

@OperationMode.Bind(OperationMode.Type.Operated, group = "Calibration")
class Calibration : LinearOperationMode() {
    private var calibratingDistance: Boolean = false

    private var drive: Drive = MecanumDrive(robot,
        robot device "motor0",
        robot device "motor3",
        robot device "motor1",
        robot device "motor2",

        alignment = 180.0
    )

    init {
        log.autoClear = true

        log
            .add("Ready!")
            .update()
    }

    override fun run() {
        while (active) {
            val gamepad = robot.gamepad[0]

            log
                .add("Rotation Constant (degrees): " + MecanumDrive.rotationConstant)
                .add("Distance Constant (cm): " + MecanumDrive.distanceConstant)
                .add("Currently Calibrating: ${if (calibratingDistance) "Distance" else "Rotation"} Constant")
                .update()

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
                MecanumDrive.distanceConstant += modifier.round(4)
            else
                MecanumDrive.rotationConstant += modifier.round(4)

            if (modifier != 0.0)
                try {
                    Thread.sleep(250)
                } catch (ignore: Exception) {
                }

            if (gamepad.a) {
                if (calibratingDistance) {
                    log.add(listOf("Calibrating Distance...", "Attempting to move robot by 2 metres.", "Distance Constant (cm): ${MecanumDrive.distanceConstant}")).update()

                    drive.move(Direction.Forward, 0.2, 200.0)
                } else {
                    log.add(listOf("Calibrating Rotation...", "Attempting to rotate robot by 16 rotations.", "Rotation Constant (degrees): ${MecanumDrive.rotationConstant}")).update()

                    drive.rotate(0.3, 360 * 16.0)
                }

                log.add(listOf("Completed Calibration Test", "Rotation Constant (degrees): ${MecanumDrive.rotationConstant}", "Distance Constant (cm): ${MecanumDrive.distanceConstant}")).update()

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