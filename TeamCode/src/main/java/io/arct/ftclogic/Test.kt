package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import io.arct.ftclib.drive.Drive
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.motor.Motor

@OperationMode.Bind(OperationMode.Type.AUTONOMOUS)
class Test(sdk: OpMode) : OperationMode(sdk) {
    private var drive: Drive? = null

    override fun init() {
        Motor.distanceConstant = 10000.0

        drive = MecanumDrive(robot, listOf("left-front", "right-front", "left-back", "right-back"))
    }

    override fun loop() {
        drive!!.move(0.0, 0.5, 10.0)
    }
}