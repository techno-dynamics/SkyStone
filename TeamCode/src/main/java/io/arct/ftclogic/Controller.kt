package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import io.arct.ftccore.OperationMode
import io.arct.ftccore.controller.DriveController
import io.arct.ftccore.controller.HolonomicDrive

@TeleOp
class Controller : OperationMode() {
    private var drive: DriveController? = null

    override fun init() {
        drive = HolonomicDrive(robot, listOf("left-front", "right-front", "left-back", "right-back"))
    }

    override fun loop() {
        drive!!.use(robot.gamepad[0])
    }
}
