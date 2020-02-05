package io.arct.ftclogic

import io.arct.ftclib.drive.MecanumDrive
import io.arct.ftclib.eventloop.LinearOperationMode
import io.arct.ftclib.eventloop.OperationMode
import io.arct.robotlib.navigation.Direction
import io.arct.robotlib.robot.device

@OperationMode.Bind(OperationMode.Type.Autonomous, name = "Autonomous (LEFT)", group = "Main")
class ProgramInverse : LinearOperationMode() {
    init {
        MecanumDrive.distanceConstant = 12.5
    }

    private val drive: MecanumDrive = MecanumDrive(robot,
        robot device "motor0",
        robot device "motor3",
        robot device "motor1",
        robot device "motor2",

        alignment = 180.0
    )

    override fun run() {
        drive.move(Direction.Forward, 0.35)
        sleep(1000)
        drive.stop()

        drive.move(Direction.Left, 0.35)
        sleep(2500)
        drive.stop()
    }
}