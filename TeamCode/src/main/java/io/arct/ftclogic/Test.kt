package io.arct.ftclogic

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import io.arct.ftccore.OperationMode
import io.arct.ftccore.controller.DriveController
import io.arct.ftccore.controller.HolonomicDrive
import io.arct.ftccore.device.Motor

@Autonomous
class Test : OperationMode() {
    private var drive: DriveController? = null

    override fun init() {
        Motor.distanceConstant = 10000.0

        HolonomicDrive.rotationConstant = 10000.0
        HolonomicDrive.distanceConstant = 10000.0

        drive = HolonomicDrive(robot, listOf("left-front", "right-front", "left-back", "right-back"))
    }

    override fun loop() {
        drive!!.move(0.0, 0.5, 10.0)
    }
}
