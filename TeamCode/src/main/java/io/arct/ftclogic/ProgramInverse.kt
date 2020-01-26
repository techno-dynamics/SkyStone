package io.arct.ftclogic

import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.util.Direction

@OperationMode.Bind(OperationMode.Type.Autonomous, name = "Autonomous (LEFT)", group = "Main")
class ProgramInverse : LinearOperationMode() {
    private lateinit var drive: MecanumDrive

    override fun init() {
        MecanumDrive.distanceConstant = 12.5

        drive = MecanumDrive(robot,
            robot.map("motor0"),
            robot.map("motor3"),
            robot.map("motor1"),
            robot.map("motor2"),

            directionConstant = 180.0
        )
    }

    override fun run() {
        drive.move(Direction.Forward, 0.35)
        sleep(1000)
        drive.stop()

        drive.move(Direction.Left, 0.35)
        sleep(2500)
        drive.stop()
    }
}