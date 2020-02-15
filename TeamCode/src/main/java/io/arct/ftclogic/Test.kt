package io.arct.ftclogic

import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.motors.FtcMotor
import io.arct.robotlib.hardware.motors.Motor
import io.arct.robotlib.robot.device

@OperationMode.Bind(OperationMode.Type.Autonomous, group = "Calibration")
class Test : LinearOperationMode() {

    init {
        FtcMotor.adjustmentPower = 0.1
        FtcMotor.targetPositionTolerance = 0.0
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
        val m: Motor = robot device "motor0"

        m.move(0.2, -100.0); Thread.sleep(100)
        m.move(0.4, 200.0); Thread.sleep(100)
        m.move(0.6, -300.0); Thread.sleep(100)
        m.move(0.8, 400.0); Thread.sleep(100)
        m.move(1.0, -500.0); Thread.sleep(100)
    }
}