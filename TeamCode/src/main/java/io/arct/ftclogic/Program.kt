package io.arct.ftclogic

import io.arct.ftc.Location
import io.arct.ftc.Vuforia
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.robotlib.hardware.motors.Servo
import io.arct.robotlib.navigation.Direction
import io.arct.robotlib.robot.device
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix
import org.firstinspires.ftc.robotcore.external.matrices.VectorF
import org.firstinspires.ftc.robotcore.external.navigation.*

@OperationMode.Bind(OperationMode.Type.Autonomous, name = "Autonomous", group = "Main")
class Program : LinearOperationMode() {
    init {
        MecanumDrive.distanceConstant = 3.8599999999999999834
        MecanumDrive.rotationConstant = 1.85500000000003
    }

    private val drive: MecanumDrive = MecanumDrive(robot,
        robot device "motor0",
        robot device "motor3",
        robot device "motor1",
        robot device "motor2",

        alignment = 180.0
    )

    private var grabber: Servo = robot device "servo4"

    init {
        grabber.position = 1.0
    }

    private val tracker = Vuforia.build(BuildConfig.VUFORIA_KEY, robot)
    private var lastLocation: OpenGLMatrix? = null
    private var targetVisible: Boolean = false

    init {
        tracker.targets.activate()
    }

    override fun run() {
        drive.move(Direction.Forward, 0.2, 55.0)
        drive.rotate(0.2, -90.0)

        drive.move(Direction.Backward, 0.5)

        var stone = skystone
//        var distance = 0.0
        while (stone == null) {
//            drive.move(Direction.Backward, 0.2, 20.0)
//            distance += 5.0
//            Thread.sleep(100)
            stone = skystone
        }
        drive.stop()

        drive.move(Direction.Right, 0.15, 45.0) // LEFT IS RIGHT AND RIGHT IS LEFT
        grabStone()
        drive.move(Direction.Left, 0.15, 45.0)

        drive.move(Direction.Forward, 0.2, 100.0)
        releaseStone()


//        while (!stopRequested && active) {
//            val loc = skystone
//
//            if (loc == null) {
//                log.add("no skystone").clear()
//                continue
//            }
//
//            log.add("loc: ${loc.x}, ${loc.y}, ${loc.z}")
//            log.add("rot: ${loc.orientation.firstAngle}, ${loc.orientation.secondAngle}, ${loc.orientation.thirdAngle}")
//            log.update()
//        }

        tracker.targets.deactivate()
    }

    private fun grabStone() {
        grabber.position = 0.0
        Thread.sleep(1000)
    }

    private fun releaseStone() {
        grabber.position = 1.0
    }

    private val skystone: Location? get() {
        targetVisible = false

        for (trackable in tracker.trackables) {
            if (trackable.name != "Stone Target")
                continue

            if (!(trackable.listener as VuforiaTrackableDefaultListener).isVisible)
                continue

            targetVisible = true

            val robotLocationTransform = (trackable.listener as VuforiaTrackableDefaultListener).updatedRobotLocation

            if (robotLocationTransform != null)
                lastLocation = robotLocationTransform

            break
        }

        if (!targetVisible)
            return null

        val translation: VectorF = lastLocation!!.translation

        val x = translation[0] / Vuforia.Constants.mmPerInch
        val y = translation[1] / Vuforia.Constants.mmPerInch
        val z = translation[2] / Vuforia.Constants.mmPerInch
        val rotation = Orientation.getOrientation(lastLocation, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES)

        return Location(x.toDouble(), y.toDouble(), z.toDouble(), rotation)
    }
}