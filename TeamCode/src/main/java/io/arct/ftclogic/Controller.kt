package io.arct.ftclogic

import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.gamepad.Gamepad
import io.arct.ftclib.hardware.motor.ContinuousServo
import io.arct.ftclib.hardware.motor.Motor
import io.arct.ftclib.hardware.motor.Servo
import io.arct.ftclib.hardware.sensor.Imu
import io.arct.ftclib.hardware.sensor.TouchSensor
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import kotlin.concurrent.thread

@OperationMode.Bind(OperationMode.Type.Operated, name = "Standard Control", group = "Main")
class Controller : OperationMode() {
    private lateinit var drive: MecanumDrive

    private lateinit var imu: Imu

    private lateinit var intakeLeft: Motor
    private lateinit var intakeRight: Motor
    private lateinit var linear: ContinuousServo

    private lateinit var buildplateLeft: Servo
    private lateinit var buildplateRight: Servo
    private lateinit var clamp: Servo
    private lateinit var pivot: ContinuousServo
    private lateinit var capstone: Servo
    private lateinit var limit: TouchSensor

    private var buildplate: Boolean = false
    private var orientation: Double = ImuOffset
    private var clampBrick: Boolean = false
    private var pivotBrick: Boolean = false
    private var pressed: Boolean = false
    private var capstoneMode: Boolean = true

    override fun init() {
        imu = robot.map("imu")
        intakeLeft = robot.map("motor4")
        intakeRight = robot.map("motor5")
        clamp = robot.map("servo0")
        pivot = robot.map("servo1")
        linear = robot.map("servo6")
        buildplateLeft = robot.map("servo2")
        buildplateRight = robot.map("servo7")
        capstone = robot.map("servo3")
        limit = robot.map("digital0")

        drive = MecanumDrive(robot,
            robot.map("motor0"),
            robot.map("motor3"),
            robot.map("motor1"),
            robot.map("motor2"),
            rotation = ::orientation
        )

        thread {
            imu.init()

            log.add("IMU Ready").update()

            while (true) {
                orientation = AngleUnit.DEGREES.fromUnit(imu.orientation.angleUnit, imu.orientation.firstAngle) + ImuOffset
            }
        }

        capstone.position = 1.0

        log.add("Ready").update()
    }

    override fun loop() {
        drive.gamepad(robot.gamepad[0])

        input(robot.gamepad[1])
        intake(robot.gamepad[1].rt >= 0.5, robot.gamepad[1].lt >= 0.5)
        linear(robot.gamepad[1].right.y)
        buildplate()
        clamp()
        pivot()
        capstone()
    }

    private fun intake(intake: Boolean, outtake: Boolean) {
        val power = if (intake) 0.75 else if (outtake) -0.75 else 0.0

        intakeLeft.power = power
        intakeRight.power = -power
    }

    private fun linear(power: Double) = if (limit.pressed && power < 0)
        linear.power = 0.0
    else
        linear.power = power

    private fun clamp() {
        clamp.position = if (clampBrick) 1.0 else 0.0
    }

    private fun pivot() {
        pivot.power = if (pivotBrick) -0.07 else 1.0
    }

    private fun capstone() {
        capstone.position = if (capstoneMode) 1.0 else 0.0
    }

    private fun buildplate() {
        buildplateLeft.position = if (buildplate) 1.0 else 0.0
        buildplateRight.position = if (buildplate) 0.0 else 1.0
    }

    private fun input(gamepad: Gamepad) {
        if (pressed && (gamepad.lb || gamepad.rb || gamepad.a || gamepad.b))
            return

        if (gamepad.lb)
            clampBrick = !clampBrick

        if (gamepad.rb)
            pivotBrick = !pivotBrick

        if (gamepad.a)
            buildplate = !buildplate

        if (gamepad.b)
            capstoneMode = !capstoneMode

        pressed = gamepad.lb || gamepad.rb || gamepad.a || gamepad.b
    }

    companion object {
        private const val ImuOffset = 180.0
    }
}