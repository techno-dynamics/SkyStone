package io.arct.ftclogic

import com.vuforia.CameraDevice
import io.arct.ftc.Location
import io.arct.ftc.VuforiaTargeting
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.sensors.FtcImu
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

    private var rotationPosition: Double = 0.0
    private val imu: FtcImu = robot device "imu"

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

    private val tracker = VuforiaTargeting.build(BuildConfig.VUFORIA_KEY, robot)
    private var lastLocation: OpenGLMatrix? = null
    private var targetVisible: Boolean = false

    init {
        tracker.targets.activate()
        CameraDevice.getInstance().setFlashTorchMode(true)
    }

    override fun run() {
        drive.move(Direction.Forward, 0.1, 60.0)
        drive.rotate(0.2, -90.0)

        rotationPosition += 90.0
        adjust()

        val distance = collectStone()
        drive.move(Direction.Forward, 0.1, 150.0 + distance)
        releaseStone()

        adjust()
        drive.move(Direction.Backward, 0.1, 150.0 + distance)
        adjust()

        val distance2 = collectStone()
        drive.move(Direction.Forward, 0.1, 150.0 + distance + distance2)
        releaseStone()

        tracker.targets.deactivate()
    }

    private fun collectStone(): Double {
        val distance = locateStone()
        drive.move(Direction.Right, 0.15, 45.0) // LEFT IS RIGHT AND RIGHT IS LEFT
        grabStone()
        drive.move(Direction.Left, 0.15, 45.0)

        return distance
    }

    private fun locateStone(): Double {
        val tick = 20.0

        var stone = skystone
        var distance = 0.0

        while (stone == null) {
            drive.move(Direction.Backward, 0.1, tick)
            distance += tick
            Thread.sleep(100)
            stone = skystone
        }

        return distance
    }

    private fun grabStone() {
        grabber.position = 0.0
        Thread.sleep(1000)
    }

    private fun releaseStone() {
        grabber.position = 1.0
    }

    private fun adjust() =
        drive.rotate(0.2, rotationPosition - orientation)

    private val orientation: Double get() =
        AngleUnit.DEGREES.fromUnit(imu.orientation.angleUnit, imu.orientation.firstAngle).toDouble()

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

        val x = translation[0] / VuforiaTargeting.Constants.mmPerInch
        val y = translation[1] / VuforiaTargeting.Constants.mmPerInch
        val z = translation[2] / VuforiaTargeting.Constants.mmPerInch
        val rotation = Orientation.getOrientation(lastLocation, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES)

        return Location(x.toDouble(), y.toDouble(), z.toDouble(), rotation)
    }
}