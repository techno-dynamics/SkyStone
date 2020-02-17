package io.arct.ftclogic

import io.arct.ftc.StoneDetector
import io.arct.ftc.StoneState
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.robotlib.drive.Drive
import io.arct.robotlib.hardware.motors.Motor
import io.arct.robotlib.hardware.motors.Servo
import io.arct.robotlib.navigation.Direction
import io.arct.robotlib.robot.device
import kotlin.math.abs

@OperationMode.Bind(OperationMode.Type.Autonomous, name = "Autonomous", group = "Main")
class Program : LinearOperationMode() {
    init {
        MecanumDrive.Constants.distance = 3.74
        MecanumDrive.Constants.rotation = 1.85
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

    private val buildplateLeft: Servo = robot device "servo2"
    private val buildplateRight: Servo = robot device "servo7"

    private val tape: Motor = robot device "motor6"
    private var mode: Int = 1 // BLUE, RED, PARK

    init {
        grabber.position = 1.0
        buildplate(false)

        val gamepad = robot.gamepad[0]
        var pressed = false
        while (!gamepad.a) {

            log.add("Currently selecting mode...").add("Alliance: " + when (mode) {
                0 -> "Blue Alliance"
                1 -> "Red Alliance"
                2 -> "Neutral Alliance"
                else -> "No program selected"
            })

            log.add("").add("Program Features:")

            if (mode == 0 || mode == 1)
                log.add(" - 1 SkyStone").add(" - Move Foundation").add(" - Park on the Line")
            else if (mode == 2)
                log.add(" - Park on the Line")
            else
                log.add("No program Selected")


            log.update()

            if (pressed && (gamepad.b || gamepad.x || gamepad.y))
                continue

            pressed = false

            mode = when {
                gamepad.x -> 0
                gamepad.b -> 1
                gamepad.y -> 2
                else -> mode
            }
        }

        log.add("Program Selected: " + when (mode) {
            0 -> "Blue"
            1 -> "Red"
            2 -> "Park"
            else -> "None"
        })

        log.add("Ready!").update()
    }

    override fun run() = when (mode) {
        0 -> program(0, Direction.Backward, 1.0)
        1 -> program(1, Direction.Forward, -1.0)
        2 -> neutral()

        else -> Unit
    }

    private fun neutral() {
        tape.power = -.75
        Thread.sleep(550)
        tape.power = 0.0
    }

    private fun program(mode: Int, directionBuild: Direction, rotationModifier: Double) {
        val position = detector.state

        drive.move(Direction.Right, 0.2, 45.0)

        val offset = when (position) {
            StoneState.One -> -17.5
            StoneState.Two -> -1.5
            StoneState.Three -> 8.5

            StoneState.Unknown -> -2.0
        }

        if (offset > 0.0)
            drive.move(Direction.Forward, .2, offset)
        else
            drive.move(Direction.Backward, .2, abs(offset))

        // Get Stone
        getStone()

        // Move Back

        drive.move(directionBuild, .5, 120.0 + (offset * rotationModifier))
        releaseStone()

        drive.move(directionBuild, .35, 85.0)

        // WALL ALIGN {
        drive.move(Direction.Left, 0.2)
        Thread.sleep(2000)
        drive.stop()
        drive.move(Direction.Right, 0.2, 45.0)
        // }

        drive.rotate(-.2, 80.0)

        drive.move(Direction.Backward, .2, 55.0)
        buildplate(true)

        drive.move(Direction.Forward, .4, 30.0)

        drive.rotate(rotationModifier * .4, 20.0)
        drive.move(Direction.Forward, .3, 20.0)
        drive.rotate(rotationModifier * .4, 95.0)

        buildplate(false)

        drive.move(Direction.Backward, .3)
        Thread.sleep(1250)
        drive.stop()

        if (mode == 0)
            drive.move(Direction.Right, 0.40, 40.0)

        tape.power = -.75
        drive.move(Direction.Forward, 0.1, 10.0)
        Thread.sleep(1750)
    }

    private fun buildplate(buildplate: Boolean) {
        buildplateLeft.position = if (buildplate) 1.0 else .0
        buildplateRight.position = if (buildplate) .0 else 1.0
        Thread.sleep(1000)
    }

    private fun getStone() {
        drive.move(Direction.Right, .1, 45.0)
        grabStone()

        drive.move(Direction.Left, .2, 45.0)
    }

    private fun grabStone() {
        grabber.position = .0
        Thread.sleep(1000)
    }

    private fun releaseStone() {
        grabber.position = 1.0
        Thread.sleep(500)
    }
}