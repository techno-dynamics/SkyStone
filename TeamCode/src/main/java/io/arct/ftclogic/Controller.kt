package io.arct.ftclogic

import io.arct.ftclib.drive.Drive
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.gamepad.Gamepad
import io.arct.ftclib.hardware.motor.ContinuousServo
import io.arct.ftclib.hardware.motor.Servo
import io.arct.ftclib.hardware.sensor.Imu
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import java.util.*
import kotlin.concurrent.thread

@OperationMode.Bind(OperationMode.Type.Operated, name = "Standard Control", group = "Main")
class Controller : OperationMode() {
    private lateinit var drive: MecanumDrive

    private var buildplate: Boolean = false
    private var clampBrick: Boolean = false

    private val intakeLeft: ContinuousServo by robot.map
    private val intakeRight: ContinuousServo by robot.map

    private val buildLeft: Servo by robot.map
    private val buildRight: Servo by robot.map

    private val arm: ContinuousServo by robot.map
    private val clamp: Servo by robot.map

    private val imu: Imu by robot.map
    private var orientation: Double = 0.0

    private var pressed: Boolean = false

    override fun init() {
        log.autoClear = false

        log.add("Initializing IMU...").update()

        imu.init()

        log.add("Done!").add("Preparing Mecanum Drive...").update()

        drive = MecanumDrive(robot,
            robot.map("left-front"),
            robot.map("right-front"),
            robot.map("left-back"),
            robot.map("right-back"),
            rotation = ::orientation
        )

        log.add("Field-Centric movement is ${if (drive.fieldCentric) "Enabled" else "Disabled"}").add("Done!").update()

        thread {
            while (true) {
                orientation = AngleUnit.DEGREES.fromUnit(imu.orientation.angleUnit, imu.orientation.firstAngle).toDouble()
            }
        }
    }

    override fun loop() {
        drive.gamepad(robot.gamepad[0])
        input(robot.gamepad[1])

        arm(robot.gamepad[1].right.y)
        buildplate(buildplate)
        clamp(clampBrick)
        intake(robot.gamepad[1].rt > 0.5, robot.gamepad[1].lt > 0.5)
    }

    private fun arm(power: Double) {
        arm.power = 0.5 * power
    }

    private fun buildplate(state: Boolean) {
        buildLeft.position = if (state) 0.0 else 1.0
        buildRight.position = if (state) 1.0 else 0.0
    }

    private fun clamp(enabled: Boolean) {
        clamp.position = if (enabled) 0.0 else 1.0
    }

    private fun intake(intake: Boolean, outtake: Boolean) {
        val state = if (intake) 1 else if (outtake) 2 else 0

        intakeLeft.power = listOf(0.0, -0.75, 0.75)[state]
        intakeRight.power = listOf(0.0, 0.75, -0.75)[state]
    }

    private fun input(gamepad: Gamepad) {
        if (pressed && (gamepad.rb || gamepad.a))
            return

        if (gamepad.rb)
            clampBrick = !clampBrick

        if (gamepad.a)
            buildplate = !buildplate

        pressed = gamepad.rb || gamepad.a
    }
}