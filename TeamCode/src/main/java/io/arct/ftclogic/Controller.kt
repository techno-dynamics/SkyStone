package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import io.arct.ftclib.drive.Drive
import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.OperationMode

@OperationMode.Bind(OperationMode.Type.OPERATED)
class Controller(sdk: OpMode) : OperationMode(sdk) {
    private var drive: Drive? = null

    override fun init() {
        drive = MecanumDrive(robot, listOf("left-front", "right-front", "left-back", "right-back"))
    }

    override fun loop() {
        drive!!.gamepad(robot.gamepad[0])
    }
}