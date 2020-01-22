package io.arct.ftclogic

import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.util.Direction

@OperationMode.Bind(OperationMode.Type.Autonomous, group = "Main")
class Program : LinearOperationMode() {
    private lateinit var drive: MecanumDrive

    override fun init() {
        MecanumDrive.distanceConstant = 12.5

        drive = MecanumDrive(robot,
            robot.map("left-front"),
            robot.map("right-front"),
            robot.map("left-back"),
            robot.map("right-back")
        )
    }

    override fun run() {
        drive.move(Direction.Forward, 0.75)
        sleep(500)
        drive.stop()

        drive.move(Direction.Right, 0.75)
        sleep(1375)
        drive.stop()
    }
}