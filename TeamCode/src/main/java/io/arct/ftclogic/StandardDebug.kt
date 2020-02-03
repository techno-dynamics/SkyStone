package io.arct.ftclogic

import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.gamepad.Gamepad
import io.arct.robotlib.hardware.motors.Motor
import io.arct.robotlib.robot.device

@OperationMode.Bind(OperationMode.Type.Operated, name = "Standard Debug", group = "Debug")
class StandardDebug : OperationMode() {
    private var motors: Array<Motor>? = null

    override fun init() {
        motors = arrayOf(robot device "motor-0", robot device "motor-1", robot device "motor-2", robot device "motor-3")

        log.autoClear = true

        log
            .add("Ready!")
            .update()
    }

    override fun loop() {
        val gamepad: Gamepad = robot.gamepad[0]

        motors!![0].power = gamepad.left.x
        motors!![1].power = gamepad.right.x
        motors!![2].power = gamepad.left.y
        motors!![3].power = gamepad.right.y
    }
}
