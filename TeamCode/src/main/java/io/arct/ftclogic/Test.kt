package io.arct.ftclogic

import io.arct.ftclib.drive.Drive
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.motor.Motor
import io.arct.ftclib.hardware.sensor.Imu

@OperationMode.Bind(OperationMode.Type.Operated, group = "Debug")
class Test : OperationMode() {
    private var drive: Drive? = null

    private val imu: Imu by robot.map

    override fun init() {
        Motor.distanceConstant = 10000.0

        drive = MecanumDrive(robot,
            robot.map("left-front"),
            robot.map("right-front"),
            robot.map("left-back"),
            robot.map("right-back"),
            rotation = imu.orientation::firstAngle
        )
    }

    override fun loop() {
        drive!!.gamepad(robot.gamepad[0])

        log
            .add("${imu.orientation.firstAngle}")
            .add("${imu.orientation.secondAngle}")
            .add("${imu.orientation.thirdAngle}")
            .add("${imu.orientation.angleUnit.toDegrees(imu.orientation.firstAngle)}")
            .add("${imu.orientation.angleUnit.toDegrees(imu.orientation.secondAngle)}")
            .add("${imu.orientation.angleUnit.toDegrees(imu.orientation.thirdAngle)}")
    }
}