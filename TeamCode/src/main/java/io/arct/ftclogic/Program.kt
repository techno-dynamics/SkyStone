package io.arct.ftclogic

import io.arct.ftc.StoneDetector
import io.arct.ftc.StoneState
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.robotlib.hardware.motors.Servo
import io.arct.robotlib.navigation.Direction
import io.arct.robotlib.robot.device

@OperationMode.Bind(OperationMode.Type.Autonomous, name = "Autonomous", group = "Main")
class Program : LinearOperationMode() {
    init {
        MecanumDrive.distanceConstant = 3.8599999999999999834
        MecanumDrive.rotationConstant = 1.85500000000003
    }

    private val detector = StoneDetector(robot, BuildConfig.VUFORIA_KEY)

    private val drive: MecanumDrive = MecanumDrive(robot,
        robot device "motor0",
        robot device "motor3",
        robot device "motor1",
        robot device "motor2",

        alignment = 180.0, autoAlign = false
    )

    private var grabber: Servo = robot device "servo4"

    init {
        grabber.position = 1.0
    }

    override fun run() {
        val position = detector.state

        drive.move(Direction.Right, 0.2, 45.0)

        when (position) {
            StoneState.One -> drive.move(Direction.Backward, 0.2, 9.5)
            StoneState.Two -> drive.move(Direction.Backward, 0.2, 0.75)
            StoneState.Three -> drive.move(Direction.Forward, 0.2, 10.0)
        }

        // Get Stone
        getStone()

        // Move Back

        drive.move(Direction.Backward, 0.2, 135.0)
        releaseStone()

        // Move to Next Stone

        drive.move(Direction.Forward, 0.2, 135.0 + 60.0) // Offset of two stones

        // Get Stone # 2

        getStone(d2 = 45.0)

        // Move Back

        drive.move(Direction.Backward, 0.3, 190.0)
        releaseStone()
    }

    private fun getStone(d1: Double = 45.0, d2: Double = 35.0) {
        drive.move(Direction.Right, 0.2, d1)
        grabStone()

        drive.move(Direction.Left, 0.2, d2)
    }

    private fun grabStone() {
        grabber.position = 0.0
        Thread.sleep(1000)
    }

    private fun releaseStone() {
        grabber.position = 1.0
        Thread.sleep(1000)
    }
}