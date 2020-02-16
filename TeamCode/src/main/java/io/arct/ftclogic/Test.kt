package io.arct.ftclogic

import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.motors.FtcMotor
import io.arct.robotlib.hardware.motors.Motor
import io.arct.robotlib.robot.device

@OperationMode.Bind(OperationMode.Type.Autonomous, group = "Calibration")
class Test : LinearOperationMode() {

    init {
        FtcMotor.Constants.adjustmentPower = 0.1
        FtcMotor.Constants.targetPositionTolerance = 0.0
    }

//    private var drive: Drive = MecanumDrive(robot,
//        robot device "motor0",
//        robot device "motor3",
//        robot device "motor1",
//        robot device "motor2",
//
//        alignment = 180.0
//    )

    override fun run() {
        val m: FtcMotor<*> = robot device "motor0"

        m.target(100.0).start(0.2).waitTarget()
        m.stop()

        m.target(-200.0).start(0.4).waitTarget()
        m.stop()

        m.target(300.0).start(0.6).waitTarget()
        m.stop()

        m.target(-400.0).start(0.8).waitTarget()
        m.stop()

        m.target(500.0).start(1.0).waitTarget()
        m.stop()
    }
}